drop table if exists Area;
create table Area
(
   id                   char(4) not null,
   province             varchar(16),
   location             varchar(16),
   primary key (id)
);

drop table if exists Custinfo;
create table Custinfo
(
   custNo               char(4) not null,
   custType             char(1),
   areaId               char(4),
   primary key (custNo)
);
