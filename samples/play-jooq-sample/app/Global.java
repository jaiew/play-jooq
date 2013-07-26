import com.google.inject.Guice;
import com.google.inject.Injector;
import modules.H2Module;
import modules.MySQLModule;
import play.Application;
import play.GlobalSettings;

/**
 * @author jaiew
 */
public class Global extends GlobalSettings {

    Injector injector;

    @Override
    public void onStart(Application application) {

        String dbName = application.configuration().getString("jooq.default.database.name");
        boolean useMysql = dbName.equals("org.jooq.util.mysql.MySQLDatabase") || application.isProd();

        injector = Guice.createInjector(useMysql ? new MySQLModule() : new H2Module());
    }

    @Override
    public <A> A getControllerInstance(Class<A> clazz) throws Exception {
        return injector.getInstance(clazz);
    }
}
