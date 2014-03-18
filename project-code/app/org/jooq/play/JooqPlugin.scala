package org.jooq.play

import play.core._

import play.api._

import db.{DBApi, DBPlugin}
import play.api.mvc.{RequestHeader}
import java.io.File
import org.jooq.util.GenerationTool
import org.jooq.util.jaxb._
import scala.Some
import play.api.mvc.SimpleResult

object Count {
  var amount=0
}

/**
 * @author jaiew
 */
class JooqPlugin(val app: Application) extends Plugin with HandleWebCommandSupport {

  lazy val dbApi = app.plugin[DBPlugin].map(_.api).getOrElse(throw new Exception("there should be a database plugin registered at this point but looks like it's not available, so JOOQ won't work. Please make sure you register a db plugin properly"))

  /**
   * Is this plugin enabled.
   *
   * {{{
   * jooqlugin = disabled
   * }}}
   */
  override lazy val enabled = app.configuration.getConfig("jooq").isDefined && {
    !app.configuration.getString("jooqplugin").exists(_ == "disabled")
  }

  override def onStart() {
    if (Count.amount == 0) {
      Count.amount += 1
      app.mode match {
        case Mode.Test => throw JooqGenerationRequired(dbApi)
        case Mode.Dev => throw JooqGenerationRequired(dbApi)
        case _ => None
      }
    }
  }

  def handleWebCommand(request: RequestHeader, sbtLink: SBTLink, path: File): Option[SimpleResult] = {

    val applyGeneration = """/@jooq/generate""".r

    lazy val redirectUrl = request.queryString.get("redirect").filterNot(_.isEmpty).map(_(0)).getOrElse("/")

    request.path match {
      case applyGeneration() => {
        Some {
          dbApi.datasources.foreach {
            case (ds, db) => {
              Logger.info("Generate JOOQ classes for: " + db)
              executeGeneration(dbApi, db)
            }
          }

          sbtLink.forceReload()
          play.api.mvc.Results.Redirect(redirectUrl)
        }
      }
      case _ => None
    }
  }

  object JooqPlugin{
     def generate(){
       dbApi.datasources.foreach {
         case (ds, db) => {
           Logger.info("Generate JOOQ classes for: " + db)
           executeGeneration(dbApi, db)
         }
     }
  }
}
  def executeGeneration(api: DBApi, db: String) = {

    val generationTool = new GenerationTool()
    implicit val connection = api.getConnection(db, autocommit = true)
    val configuration = new org.jooq.util.jaxb.Configuration()
    val generator = new Generator()
    val strategy = new Strategy()
    val database = new Database()
    val generate = new Generate()
    val target = new Target()

    val jooqConfig = app.configuration.getConfig("jooq." + db).get

    generationTool.setConnection(connection)

    val databaseName = jooqConfig.getString("database.name").getOrElse({
      Logger.error("No database.name property for $db")
      ""
    })
    val databaseSchema = jooqConfig.getString("database.schema").getOrElse({
      Logger.error("No database.schema property for $db")
      ""
    })

    generator.setName(jooqConfig.getString("generator.name").getOrElse("org.jooq.util.JavaGenerator"))
    //strategy
    strategy.setName(jooqConfig.getString("strategy.name").getOrElse("org.jooq.util.DefaultGeneratorStrategy"))
    generator.setStrategy(strategy)

    //database
    database.setName(databaseName)
    database.setInputSchema(databaseSchema)
    database.setIncludes(jooqConfig.getString("database.includes").getOrElse(".*"))
    database.setExcludes(jooqConfig.getString("database.excludes").getOrElse(""))
    generator.setDatabase(database)

    //generate
    generate.setRelations(jooqConfig.getBoolean("generate.relations").getOrElse(true).booleanValue())
    generate.setDeprecated(jooqConfig.getBoolean("generate.deprecated").getOrElse(true).booleanValue())
    generate.setInstanceFields(jooqConfig.getBoolean("generate.instanceFields").getOrElse(true).booleanValue())
    generate.setGeneratedAnnotation(jooqConfig.getBoolean("generate.generatedAnnotation").getOrElse(true).booleanValue())
    generate.setRecords(jooqConfig.getBoolean("generate.records").getOrElse(true).booleanValue())
    generate.setPojos(jooqConfig.getBoolean("generate.pojos").getOrElse(false).booleanValue())
    generate.setImmutablePojos(jooqConfig.getBoolean("generate.immutablePojos").getOrElse(false).booleanValue())
    generate.setInterfaces(jooqConfig.getBoolean("generate.interfaces").getOrElse(false).booleanValue())
    generate.setDaos(jooqConfig.getBoolean("generate.daos").getOrElse(false).booleanValue())
    generate.setJpaAnnotations(jooqConfig.getBoolean("generate.jpaAnnotations").getOrElse(false).booleanValue())
    generate.setValidationAnnotations(jooqConfig.getBoolean("generate.validationAnnotations").getOrElse(false).booleanValue())
    generate.setGlobalObjectReferences(jooqConfig.getBoolean("generate.globalObjectReferences").getOrElse(true).booleanValue())
    generate.setFluentSetters(jooqConfig.getBoolean("generate.fluentSetters").getOrElse(false).booleanValue())
    generator.setGenerate(generate)

    //target
    target.setDirectory(jooqConfig.getString("target.directory").getOrElse("./app"))
    target.setPackageName(jooqConfig.getString("target.package").getOrElse("org.jooq.generated"))
    generator.setTarget(target)
    configuration.setGenerator(generator)

    //run it
    generationTool.run(configuration)
    connection.close()
  }

}

/**
 * Exception thrown when JOOQ generation needs to be run
 *
 * @param api Database API
 */
case class JooqGenerationRequired(api: DBApi) extends PlayException.RichDescription(
  "JOOQ generation needs to be run!",
  "") {

  val databases = StringBuilder.newBuilder

  api.datasources.foreach {
    case (ds, db) => {
      databases.append(" - ")
      databases.append(db)
      databases.append("\n")
    }
  }

  def subTitle = "The following databases will be generated:"
  def content = databases.toString()

  private val javascript = """
        document.location = '/@jooq/generate?redirect=' + encodeURIComponent(location)
                           """.format().trim

  def htmlDescription = {

    <span>JOOQ generation will be run-</span>
        <input name="jooq-button" type="button" value="Run JOOQ generation!" onclick={ javascript }/>

  }.mkString

}

