# --- !Ups

CREATE TABLE `task` (
 `id` bigint(20) NOT NULL auto_increment,
 `label` varchar(255) DEFAULT NULL,
 PRIMARY KEY (`id`)
);

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table `task`;

SET FOREIGN_KEY_CHECKS=1;

