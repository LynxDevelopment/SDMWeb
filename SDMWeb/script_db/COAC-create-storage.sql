
     create table HS_CA_EVENTDETAILS (
         ID numeric(19,0) not null,
         VERSION int not null,
         REFID numeric(19,0) null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         ISDELETED tinyint not null,
         EXPIRATIONDATE datetime null,
         BOOLEAN1 tinyint null,
         BOOLEAN2 tinyint null,
         BOOLEAN3 tinyint null,
         BOOLEAN4 tinyint null,
         BOOLEAN5 tinyint null,
         BOOLEAN6 tinyint null,
         BOOLEAN7 tinyint null,
         BOOLEAN8 tinyint null,
         BOOLEAN9 tinyint null,
         BOOLEAN10 tinyint null,
         LONG1 numeric(19,0) null,
         LONG2 numeric(19,0) null,
         LONG3 numeric(19,0) null,
         LONG4 numeric(19,0) null,
         LONG5 numeric(19,0) null,
         LONG6 numeric(19,0) null,
         LONG7 numeric(19,0) null,
         LONG8 numeric(19,0) null,
         LONG9 numeric(19,0) null,
         LONG10 numeric(19,0) null,
         DOUBLE1 double precision null,
         DOUBLE2 double precision null,
         DOUBLE3 double precision null,
         DOUBLE4 double precision null,
         DOUBLE5 double precision null,
         DOUBLE6 double precision null,
         DOUBLE7 double precision null,
         DOUBLE8 double precision null,
         DOUBLE9 double precision null,
         DOUBLE10 double precision null,
         TIMESTAMP1 numeric(19,0) null,
         TIMESTAMP2 numeric(19,0) null,
         TIMESTAMP3 numeric(19,0) null,
         TIMESTAMP4 numeric(19,0) null,
         TIMESTAMP5 numeric(19,0) null,
         TIMESTAMP6 numeric(19,0) null,
         TIMESTAMP7 numeric(19,0) null,
         TIMESTAMP8 numeric(19,0) null,
         TIMESTAMP9 numeric(19,0) null,
         TIMESTAMP10 numeric(19,0) null,
         SHORTSTRING1 varchar(16) null,
         SHORTSTRING2 varchar(16) null,
         SHORTSTRING3 varchar(16) null,
         SHORTSTRING4 varchar(16) null,
         SHORTSTRING5 varchar(16) null,
         SHORTSTRING6 varchar(16) null,
         SHORTSTRING7 varchar(16) null,
         SHORTSTRING8 varchar(16) null,
         SHORTSTRING9 varchar(16) null,
         SHORTSTRING10 varchar(16) null,
         MIDDLESTRING1 varchar(64) null,
         MIDDLESTRING2 varchar(64) null,
         MIDDLESTRING3 varchar(64) null,
         MIDDLESTRING4 varchar(64) null,
         MIDDLESTRING5 varchar(64) null,
         MIDDLESTRING6 varchar(64) null,
         MIDDLESTRING7 varchar(64) null,
         MIDDLESTRING8 varchar(64) null,
         MIDDLESTRING9 varchar(64) null,
         MIDDLESTRING10 varchar(64) null,
         LONGSTRING1 varchar(128) null,
         LONGSTRING2 varchar(128) null,
         LONGSTRING3 varchar(128) null,
         LONGSTRING4 varchar(128) null,
         LONGSTRING5 varchar(128) null,
         LONGSTRING6 varchar(128) null,
         LONGSTRING7 varchar(128) null,
         LONGSTRING8 varchar(128) null,
         LONGSTRING9 varchar(128) null,
         LONGSTRING10 varchar(128) null,
         VERYLONGSTRING1 varchar(256) null,
         VERYLONGSTRING2 varchar(256) null,
         VERYLONGSTRING3 varchar(256) null,
         VERYLONGSTRING4 varchar(256) null,
         VERYLONGSTRING5 varchar(256) null,
         VERYLONGSTRING6 varchar(256) null,
         VERYLONGSTRING7 varchar(256) null,
         VERYLONGSTRING8 varchar(256) null,
         VERYLONGSTRING9 varchar(256) null,
         VERYLONGSTRING10 varchar(256) null,
         EXECUTIONDATE datetime null,
         SUSCRIPTIONDATE datetime null,
         OPERATIONALDATE datetime null,
         primary key (ID)
     );
     create table HS_CA_EVENTEXTDETAILS (
         ID numeric(19,0) not null,
         VERSION int not null,
         EXTENSIONNAME varchar(16) not null,
         EXTENSIONNUMBER int not null,
         REFID numeric(19,0) null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         ISDELETED tinyint not null,
         BOOLEAN1 tinyint null,
         BOOLEAN2 tinyint null,
         BOOLEAN3 tinyint null,
         BOOLEAN4 tinyint null,
         BOOLEAN5 tinyint null,
         BOOLEAN6 tinyint null,
         BOOLEAN7 tinyint null,
         BOOLEAN8 tinyint null,
         BOOLEAN9 tinyint null,
         BOOLEAN10 tinyint null,
         LONG1 numeric(19,0) null,
         LONG2 numeric(19,0) null,
         LONG3 numeric(19,0) null,
         LONG4 numeric(19,0) null,
         LONG5 numeric(19,0) null,
         LONG6 numeric(19,0) null,
         LONG7 numeric(19,0) null,
         LONG8 numeric(19,0) null,
         LONG9 numeric(19,0) null,
         LONG10 numeric(19,0) null,
         DOUBLE1 double precision null,
         DOUBLE2 double precision null,
         DOUBLE3 double precision null,
         DOUBLE4 double precision null,
         DOUBLE5 double precision null,
         DOUBLE6 double precision null,
         DOUBLE7 double precision null,
         DOUBLE8 double precision null,
         DOUBLE9 double precision null,
         DOUBLE10 double precision null,
         TIMESTAMP1 numeric(19,0) null,
         TIMESTAMP2 numeric(19,0) null,
         TIMESTAMP3 numeric(19,0) null,
         TIMESTAMP4 numeric(19,0) null,
         TIMESTAMP5 numeric(19,0) null,
         TIMESTAMP6 numeric(19,0) null,
         TIMESTAMP7 numeric(19,0) null,
         TIMESTAMP8 numeric(19,0) null,
         TIMESTAMP9 numeric(19,0) null,
         TIMESTAMP10 numeric(19,0) null,
         SHORTSTRING1 varchar(16) null,
         SHORTSTRING2 varchar(16) null,
         SHORTSTRING3 varchar(16) null,
         SHORTSTRING4 varchar(16) null,
         SHORTSTRING5 varchar(16) null,
         SHORTSTRING6 varchar(16) null,
         SHORTSTRING7 varchar(16) null,
         SHORTSTRING8 varchar(16) null,
         SHORTSTRING9 varchar(16) null,
         SHORTSTRING10 varchar(16) null,
         MIDDLESTRING1 varchar(64) null,
         MIDDLESTRING2 varchar(64) null,
         MIDDLESTRING3 varchar(64) null,
         MIDDLESTRING4 varchar(64) null,
         MIDDLESTRING5 varchar(64) null,
         MIDDLESTRING6 varchar(64) null,
         MIDDLESTRING7 varchar(64) null,
         MIDDLESTRING8 varchar(64) null,
         MIDDLESTRING9 varchar(64) null,
         MIDDLESTRING10 varchar(64) null,
         LONGSTRING1 varchar(128) null,
         LONGSTRING2 varchar(128) null,
         LONGSTRING3 varchar(128) null,
         LONGSTRING4 varchar(128) null,
         LONGSTRING5 varchar(128) null,
         LONGSTRING6 varchar(128) null,
         LONGSTRING7 varchar(128) null,
         LONGSTRING8 varchar(128) null,
         LONGSTRING9 varchar(128) null,
         LONGSTRING10 varchar(128) null,
         VERYLONGSTRING1 varchar(256) null,
         VERYLONGSTRING2 varchar(256) null,
         VERYLONGSTRING3 varchar(256) null,
         VERYLONGSTRING4 varchar(256) null,
         VERYLONGSTRING5 varchar(256) null,
         VERYLONGSTRING6 varchar(256) null,
         VERYLONGSTRING7 varchar(256) null,
         VERYLONGSTRING8 varchar(256) null,
         VERYLONGSTRING9 varchar(256) null,
         VERYLONGSTRING10 varchar(256) null,
         EXTENSIONIDCODE varchar(255) null,
         NARRATIVE text null,
         FKEVENTDETAIL numeric(19,0) not null,
         primary key (ID),
         unique (FKEVENTDETAIL, EXTENSIONNAME, EXTENSIONNUMBER, VERSION)
     );
     create table HS_CA_EVENTMESSAGEFIELDS (
         ID numeric(19,0) not null,
         FIELDTYPE int not null,
         PATH varchar(128) not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         VALUESHORT varchar(16) null,
         VALUELONG varchar(128) null,
         VALUEVERYLONG varchar(256) null,
         valueClob text null,
         FKHSEVENTMESSAGE numeric(19,0) not null,
         primary key (ID)
     );
     create table HS_CA_EVENTMESSAGES (
         ID numeric(19,0) not null,
         VERSION int not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         SECURITYTYPE varchar(64) null,
         ISINPORTFOLIO tinyint null,
         SECURITY varchar(64) null,
         ISEXTENSION tinyint null,
         EVENTTYPE varchar(64) null,
         TRANSMESSKEY varchar(128) null,
         ISENDED tinyint not null,
         ISTRANSMESSERROR tinyint not null,
         TRANSMESSPARM varchar(256) null,
         FKEVENT numeric(19,0) null,
         ORIGINID varchar(16) null,
         ORIGINNAME varchar(512) null,
         ORIGINTYPE varchar(64) null,
         ORIGINPOSITION int null,
         MESSAGEID varchar(128) null,
         OPERATION varchar(64) null,
         SENDER varchar(64) null,
         EXTENSIONREF varchar(64) null,
         RECEIVER varchar(64) null,
         EVENTREFERENCE varchar(64) null,
         EVENTMESSGREF varchar(64) null,
         PREVEVENTMESSGREF varchar(64) null,
         SECURITYNAME varchar(128) null,
         ANNOUNCEDATE datetime null,
         EFFECTIVEDATE datetime null,
         SENDERTIMESTAMP datetime null,
         ACCOUNT varchar(64) null,
         AMOUNT numeric(19,0) null,
         ORIGINALMESSAGE text null,
         OPERATIONNORM varchar(6) null,
         ISMAINMARKET tinyint null,
         FKEVENTTYPE varchar(4) null,
         FKSECURITY numeric(19,0) null,
         FKSTATEFLOW varchar(16) not null,
         FKSTATE varchar(16) not null,
         FKPROVIDER varchar(16) null,
         FKFORMAT varchar(16) null,
         FKTYPE varchar(16) null,
         primary key (ID)
     );
     create table HS_CA_EVENTS (
         EVENTCLASS varchar(32) not null,
         ID numeric(19,0) not null,
         VERSION int not null,
         ISMANDATORY tinyint not null,
         REFID numeric(19,0) null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         ISDELETED tinyint not null,
         EXPIRATIONDATE datetime null,
         EXECUTIONDATE datetime null,
         SUBSCRIPTIONDATE datetime null,
         OPERATIONALDATE datetime null,
         ISPROVUPDATED tinyint not null,
         ISPROVCANCELLED tinyint not null,
         TRANSMESSKEY varchar(128) null,
         ISENDED tinyint not null,
         ISTRANSMESSERROR tinyint not null,
         TRANSMESSPARM varchar(256) null,
         ISCOMPLETE tinyint null,
         ENTITYOPERATIONALDATE datetime null,
         FKEVENTTYPE varchar(4) not null,
         FKEVENTDETAIL numeric(19,0) null,
         FKSTATEFLOW varchar(16) not null,
         FKSTATE varchar(16) not null,
         FKEVENTGROUP numeric(19,0) null,
         FKSECURITY numeric(19,0) null,
         FKMASTEREVENT numeric(19,0) null,
         FKEVENTPROVIDER varchar(16) null,
         primary key (ID)
     );
     create table HS_SP_SECURITYPORTFOLIO (
         ID numeric(19,0) not null,
         VERSION int not null,
         REFID numeric(19,0) null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         ISDELETED tinyint not null,
         CUSTOMERAMOUNT numeric(19,0) null,
         CUSTODIANAMOUNT numeric(19,0) null,
         EMISSIONDATE datetime not null,
         FKCUSTOMER numeric(19,0) not null,
         FKSECURITY numeric(19,0) not null,
         primary key (ID)
     );
     create table TB_ANSWERS (
         POLL_ID int not null,
         ANSWER_TEXT varchar(200) null,
         HIT numeric(19,0) null,
         INITIAL_HIT numeric(19,0) null,
         ANSWERS_ID int not null,
         primary key (POLL_ID, ANSWERS_ID)
     );
     create table TB_APPLICATIONS (
         ID varchar(16) not null,
         NAME varchar(64) not null,
         DESCRIPTION varchar(128) null,
         version int not null,
         SOFTWAREVERSION numeric(10,6) null,
         SOFTWAREDATE datetime null,
         DATABASEVERSION numeric(10,6) null,
         DATABASEDATE datetime null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         primary key (ID)
     );
     create table TB_CATEGORIES (
         CATEGORY_ID int not null,
         CATEGORY_NAME varchar(250) null,
         DESCRIPTION varchar(250) null,
         CHILD_INDEX int null,
         ENABLED tinyint null,
         MARKED tinyint null,
         ICON1 varchar(250) null,
         ICON2 varchar(250) null,
         PARENT_ID int null,
         MASTER_CONTENT_ID int null,
         CONTENT_LANGUAGE varchar(255) null,
         primary key (CATEGORY_ID)
     );
     create table TB_CATEGORY_PERMISSION (
         CATEGORY_ID int not null,
         PRINCIPAL varchar(50) null,
         PRINCIPAL_TYPE varchar(20) null,
         READ_PERMISSION tinyint null,
         WRITE_PERMISSION tinyint null
     );
     create table TB_CA_ALERTS (
         ID numeric(19,0) identity not null,
         QUERY varchar(256) not null,
         DESCRIPTION varchar(256) not null,
         VERSION int not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         LABEL varchar(256) not null,
         GRAPH varchar(1) not null,
         USER_ROLE varchar(2) not null,
         LINK varchar(128) null,
         primary key (ID)
     );
     create table TB_CA_ANSWERTYPES (
         ID varchar(16) not null,
         NAME varchar(64) not null,
         DESCRIPTION varchar(256) null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         primary key (ID)
     );
     create table TB_CA_DOMAINCLUSTERFORMATS (
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         FKAPPLICATION varchar(16) not null,
         FKDOMAIN varchar(16) not null,
         FKDOMAINCLUSTER varchar(32)  not null,
         FKMESSAGEFORMAT varchar(16) not null,
         primary key (FKMESSAGEFORMAT, FKAPPLICATION, FKDOMAIN, FKDOMAINCLUSTER)
     );
     create table TB_CA_EVENTCONFIGFIELDS (
         ID numeric(19,0) identity not null,
         VERSION int not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         ISREQUIRED tinyint not null,
         FKEVENTCONFIG numeric(19,0) not null,
         FKEVENTTYPEDETAIL numeric(19,0) not null,
         primary key (ID),
         unique (FKEVENTCONFIG, FKEVENTTYPEDETAIL)
     );
     create table TB_CA_EVENTCONFIGMSGFLDS (
         ID numeric(19,0) identity not null,
         VERSION int not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         NORMSCRIPT varchar(2048) null,
         FKFORMAT varchar(16) not null,
         FKMSGTYPE varchar(16) not null,
         FKEVENTFIELDCONFIG numeric(19,0) not null,
         FKEVENTPROVIDER varchar(16) null,
         primary key (ID),
         unique (FKEVENTFIELDCONFIG, FKFORMAT, FKMSGTYPE, FKEVENTPROVIDER)
     );
     create table TB_CA_EVENTCONFIGS (
         ID numeric(19,0) identity not null,
         VERSION int not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         PRIMSECNOTFOUND varchar(32) not null,
         CUSTMANNOTFOUND varchar(32) not null,
         FKSECPREFPROV2 varchar(16) null,
         FKPREFPROVIDER1 varchar(16) not null,
         FKEVENTTYPE varchar(4) not null,
         primary key (ID),
         unique (FKEVENTTYPE)
     );
     create table TB_CA_EVENTDETAILS (
         ID numeric(19,0) identity not null,
         VERSION int not null,
         REFID numeric(19,0) null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         ISDELETED tinyint not null,
         EXPIRATIONDATE datetime null,
         BOOLEAN1 tinyint null,
         BOOLEAN2 tinyint null,
         BOOLEAN3 tinyint null,
         BOOLEAN4 tinyint null,
         BOOLEAN5 tinyint null,
         BOOLEAN6 tinyint null,
         BOOLEAN7 tinyint null,
         BOOLEAN8 tinyint null,
         BOOLEAN9 tinyint null,
         BOOLEAN10 tinyint null,
         LONG1 numeric(19,0) null,
         LONG2 numeric(19,0) null,
         LONG3 numeric(19,0) null,
         LONG4 numeric(19,0) null,
         LONG5 numeric(19,0) null,
         LONG6 numeric(19,0) null,
         LONG7 numeric(19,0) null,
         LONG8 numeric(19,0) null,
         LONG9 numeric(19,0) null,
         LONG10 numeric(19,0) null,
         DOUBLE1 double precision null,
         DOUBLE2 double precision null,
         DOUBLE3 double precision null,
         DOUBLE4 double precision null,
         DOUBLE5 double precision null,
         DOUBLE6 double precision null,
         DOUBLE7 double precision null,
         DOUBLE8 double precision null,
         DOUBLE9 double precision null,
         DOUBLE10 double precision null,
         TIMESTAMP1 numeric(19,0) null,
         TIMESTAMP2 numeric(19,0) null,
         TIMESTAMP3 numeric(19,0) null,
         TIMESTAMP4 numeric(19,0) null,
         TIMESTAMP5 numeric(19,0) null,
         TIMESTAMP6 numeric(19,0) null,
         TIMESTAMP7 numeric(19,0) null,
         TIMESTAMP8 numeric(19,0) null,
         TIMESTAMP9 numeric(19,0) null,
         TIMESTAMP10 numeric(19,0) null,
         SHORTSTRING1 varchar(16) null,
         SHORTSTRING2 varchar(16) null,
         SHORTSTRING3 varchar(16) null,
         SHORTSTRING4 varchar(16) null,
         SHORTSTRING5 varchar(16) null,
         SHORTSTRING6 varchar(16) null,
         SHORTSTRING7 varchar(16) null,
         SHORTSTRING8 varchar(16) null,
         SHORTSTRING9 varchar(16) null,
         SHORTSTRING10 varchar(16) null,
         MIDDLESTRING1 varchar(64) null,
         MIDDLESTRING2 varchar(64) null,
         MIDDLESTRING3 varchar(64) null,
         MIDDLESTRING4 varchar(64) null,
         MIDDLESTRING5 varchar(64) null,
         MIDDLESTRING6 varchar(64) null,
         MIDDLESTRING7 varchar(64) null,
         MIDDLESTRING8 varchar(64) null,
         MIDDLESTRING9 varchar(64) null,
         MIDDLESTRING10 varchar(64) null,
         LONGSTRING1 varchar(128) null,
         LONGSTRING2 varchar(128) null,
         LONGSTRING3 varchar(128) null,
         LONGSTRING4 varchar(128) null,
         LONGSTRING5 varchar(128) null,
         LONGSTRING6 varchar(128) null,
         LONGSTRING7 varchar(128) null,
         LONGSTRING8 varchar(128) null,
         LONGSTRING9 varchar(128) null,
         LONGSTRING10 varchar(128) null,
         VERYLONGSTRING1 varchar(256) null,
         VERYLONGSTRING2 varchar(256) null,
         VERYLONGSTRING3 varchar(256) null,
         VERYLONGSTRING4 varchar(256) null,
         VERYLONGSTRING5 varchar(256) null,
         VERYLONGSTRING6 varchar(256) null,
         VERYLONGSTRING7 varchar(256) null,
         VERYLONGSTRING8 varchar(256) null,
         VERYLONGSTRING9 varchar(256) null,
         VERYLONGSTRING10 varchar(256) null,
         EXECUTIONDATE datetime null,
         SUSCRIPTIONDATE datetime null,
         OPERATIONALDATE datetime null,
         primary key (ID)
     );
     create table TB_CA_EVENTEXTDETAILS (
         ID numeric(19,0) identity not null,
         VERSION int not null,
         EXTENSIONNAME varchar(16) not null,
         EXTENSIONNUMBER int not null,
         REFID numeric(19,0) null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         ISDELETED tinyint not null,
         BOOLEAN1 tinyint null,
         BOOLEAN2 tinyint null,
         BOOLEAN3 tinyint null,
         BOOLEAN4 tinyint null,
         BOOLEAN5 tinyint null,
         BOOLEAN6 tinyint null,
         BOOLEAN7 tinyint null,
         BOOLEAN8 tinyint null,
         BOOLEAN9 tinyint null,
         BOOLEAN10 tinyint null,
         LONG1 numeric(19,0) null,
         LONG2 numeric(19,0) null,
         LONG3 numeric(19,0) null,
         LONG4 numeric(19,0) null,
         LONG5 numeric(19,0) null,
         LONG6 numeric(19,0) null,
         LONG7 numeric(19,0) null,
         LONG8 numeric(19,0) null,
         LONG9 numeric(19,0) null,
         LONG10 numeric(19,0) null,
         DOUBLE1 double precision null,
         DOUBLE2 double precision null,
         DOUBLE3 double precision null,
         DOUBLE4 double precision null,
         DOUBLE5 double precision null,
         DOUBLE6 double precision null,
         DOUBLE7 double precision null,
         DOUBLE8 double precision null,
         DOUBLE9 double precision null,
         DOUBLE10 double precision null,
         TIMESTAMP1 numeric(19,0) null,
         TIMESTAMP2 numeric(19,0) null,
         TIMESTAMP3 numeric(19,0) null,
         TIMESTAMP4 numeric(19,0) null,
         TIMESTAMP5 numeric(19,0) null,
         TIMESTAMP6 numeric(19,0) null,
         TIMESTAMP7 numeric(19,0) null,
         TIMESTAMP8 numeric(19,0) null,
         TIMESTAMP9 numeric(19,0) null,
         TIMESTAMP10 numeric(19,0) null,
         SHORTSTRING1 varchar(16) null,
         SHORTSTRING2 varchar(16) null,
         SHORTSTRING3 varchar(16) null,
         SHORTSTRING4 varchar(16) null,
         SHORTSTRING5 varchar(16) null,
         SHORTSTRING6 varchar(16) null,
         SHORTSTRING7 varchar(16) null,
         SHORTSTRING8 varchar(16) null,
         SHORTSTRING9 varchar(16) null,
         SHORTSTRING10 varchar(16) null,
         MIDDLESTRING1 varchar(64) null,
         MIDDLESTRING2 varchar(64) null,
         MIDDLESTRING3 varchar(64) null,
         MIDDLESTRING4 varchar(64) null,
         MIDDLESTRING5 varchar(64) null,
         MIDDLESTRING6 varchar(64) null,
         MIDDLESTRING7 varchar(64) null,
         MIDDLESTRING8 varchar(64) null,
         MIDDLESTRING9 varchar(64) null,
         MIDDLESTRING10 varchar(64) null,
         LONGSTRING1 varchar(128) null,
         LONGSTRING2 varchar(128) null,
         LONGSTRING3 varchar(128) null,
         LONGSTRING4 varchar(128) null,
         LONGSTRING5 varchar(128) null,
         LONGSTRING6 varchar(128) null,
         LONGSTRING7 varchar(128) null,
         LONGSTRING8 varchar(128) null,
         LONGSTRING9 varchar(128) null,
         LONGSTRING10 varchar(128) null,
         VERYLONGSTRING1 varchar(256) null,
         VERYLONGSTRING2 varchar(256) null,
         VERYLONGSTRING3 varchar(256) null,
         VERYLONGSTRING4 varchar(256) null,
         VERYLONGSTRING5 varchar(256) null,
         VERYLONGSTRING6 varchar(256) null,
         VERYLONGSTRING7 varchar(256) null,
         VERYLONGSTRING8 varchar(256) null,
         VERYLONGSTRING9 varchar(256) null,
         VERYLONGSTRING10 varchar(256) null,
         EXTENSIONIDCODE varchar(255) null,
         NARRATIVE text null,
         FKEVENTDETAIL numeric(19,0) not null,
         primary key (ID),
         unique (FKEVENTDETAIL, EXTENSIONNAME, EXTENSIONNUMBER, VERSION)
     );
     create table TB_CA_EVENTHOLDINGANSWER (
         ID numeric(19,0) identity not null,
         VERSION int not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         BOOLEAN1 tinyint null,
         BOOLEAN2 tinyint null,
         BOOLEAN3 tinyint null,
         BOOLEAN4 tinyint null,
         BOOLEAN5 tinyint null,
         BOOLEAN6 tinyint null,
         BOOLEAN7 tinyint null,
         BOOLEAN8 tinyint null,
         BOOLEAN9 tinyint null,
         BOOLEAN10 tinyint null,
         LONG1 numeric(19,0) null,
         LONG2 numeric(19,0) null,
         LONG3 numeric(19,0) null,
         LONG4 numeric(19,0) null,
         LONG5 numeric(19,0) null,
         LONG6 numeric(19,0) null,
         LONG7 numeric(19,0) null,
         LONG8 numeric(19,0) null,
         LONG9 numeric(19,0) null,
         LONG10 numeric(19,0) null,
         DOUBLE1 double precision null,
         DOUBLE2 double precision null,
         DOUBLE3 double precision null,
         DOUBLE4 double precision null,
         DOUBLE5 double precision null,
         DOUBLE6 double precision null,
         DOUBLE7 double precision null,
         DOUBLE8 double precision null,
         DOUBLE9 double precision null,
         DOUBLE10 double precision null,
         TIMESTAMP1 numeric(19,0) null,
         TIMESTAMP2 numeric(19,0) null,
         TIMESTAMP3 numeric(19,0) null,
         TIMESTAMP4 numeric(19,0) null,
         TIMESTAMP5 numeric(19,0) null,
         TIMESTAMP6 numeric(19,0) null,
         TIMESTAMP7 numeric(19,0) null,
         TIMESTAMP8 numeric(19,0) null,
         TIMESTAMP9 numeric(19,0) null,
         TIMESTAMP10 numeric(19,0) null,
         SHORTSTRING1 varchar(16) null,
         SHORTSTRING2 varchar(16) null,
         SHORTSTRING3 varchar(16) null,
         SHORTSTRING4 varchar(16) null,
         SHORTSTRING5 varchar(16) null,
         SHORTSTRING6 varchar(16) null,
         SHORTSTRING7 varchar(16) null,
         SHORTSTRING8 varchar(16) null,
         SHORTSTRING9 varchar(16) null,
         SHORTSTRING10 varchar(16) null,
         MIDDLESTRING1 varchar(64) null,
         MIDDLESTRING2 varchar(64) null,
         MIDDLESTRING3 varchar(64) null,
         MIDDLESTRING4 varchar(64) null,
         MIDDLESTRING5 varchar(64) null,
         MIDDLESTRING6 varchar(64) null,
         MIDDLESTRING7 varchar(64) null,
         MIDDLESTRING8 varchar(64) null,
         MIDDLESTRING9 varchar(64) null,
         MIDDLESTRING10 varchar(64) null,
         LONGSTRING1 varchar(128) null,
         LONGSTRING2 varchar(128) null,
         LONGSTRING3 varchar(128) null,
         LONGSTRING4 varchar(128) null,
         LONGSTRING5 varchar(128) null,
         LONGSTRING6 varchar(128) null,
         LONGSTRING7 varchar(128) null,
         LONGSTRING8 varchar(128) null,
         LONGSTRING9 varchar(128) null,
         LONGSTRING10 varchar(128) null,
         VERYLONGSTRING1 varchar(256) null,
         VERYLONGSTRING2 varchar(256) null,
         VERYLONGSTRING3 varchar(256) null,
         VERYLONGSTRING4 varchar(256) null,
         VERYLONGSTRING5 varchar(256) null,
         VERYLONGSTRING6 varchar(256) null,
         VERYLONGSTRING7 varchar(256) null,
         VERYLONGSTRING8 varchar(256) null,
         VERYLONGSTRING9 varchar(256) null,
         VERYLONGSTRING10 varchar(256) null,
         CHAR1 char(1) null,
         CHAR2 char(1) null,
         CHAR3 char(1) null,
         CHAR4 char(1) null,
         CHAR5 char(1) null,
         CHAR6 char(1) null,
         CHAR7 char(1) null,
         CHAR8 char(1) null,
         CHAR9 char(1) null,
         CHAR10 char(1) null,
         FKHOLDING numeric(19,0) not null,
         FKQUESTION numeric(19,0) not null,
         primary key (ID)
     );
     create table TB_CA_EVENTMESSAGEFIELDS (
         ID numeric(19,0) identity not null,
         FIELDTYPE int not null,
         PATH varchar(128) not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         VALUESHORT varchar(16) null,
         VALUELONG varchar(128) null,
         VALUEVERYLONG varchar(256) null,
         valueClob text null,
         FKEVENTMESSAGE numeric(19,0) not null,
         primary key (ID)
     );
     create table TB_CA_EVENTMESSAGES (
         ID numeric(19,0) identity not null,
         VERSION int not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         SECURITYTYPE varchar(64) null,
         ISINPORTFOLIO tinyint null,
         SECURITY varchar(64) null,
         ISEXTENSION tinyint null,
         EVENTTYPE varchar(64) null,
         TRANSMESSKEY varchar(128) null,
         ISENDED tinyint not null,
         ISTRANSMESSERROR tinyint not null,
         TRANSMESSPARM varchar(256) null,
         FKEVENT numeric(19,0) null,
         ORIGINID varchar(16) null,
         ORIGINNAME varchar(512) null,
         ORIGINTYPE varchar(64) null,
         ORIGINPOSITION int null,
         MESSAGEID varchar(128) null,
         OPERATION varchar(64) null,
         SENDER varchar(64) null,
         EXTENSIONREF varchar(64) null,
         RECEIVER varchar(64) null,
         EVENTREFERENCE varchar(64) null,
         EVENTMESSGREF varchar(64) null,
         PREVEVENTMESSGREF varchar(64) null,
         SECURITYNAME varchar(128) null,
         ANNOUNCEDATE datetime null,
         EFFECTIVEDATE datetime null,
         SENDERTIMESTAMP datetime null,
         ACCOUNT varchar(64) null,
         AMOUNT numeric(19,0) null,
         ORIGINALMESSAGE text null,
         OPERATIONNORM varchar(6) null,
         ISMAINMARKET tinyint null,
         FKFORMAT varchar(16) null,
         FKTYPE varchar(16) null,
         FKPROVIDER varchar(16) null,
         FKSTATEFLOW varchar(16) not null,
         FKSTATE varchar(16) not null,
         FKSECURITY numeric(19,0) null,
         FKEVENTTYPE varchar(4) null,
         primary key (ID)
     );
     create table TB_CA_EVENTMRCONFIGS (
         ID numeric(19,0) identity not null,
         TARGET varchar(64) null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         RULEORDER int null,
         CONDITION varchar(32) null,
         ONTRUERESULT varchar(32) null,
         ONFALSERESULT varchar(32) null,
         CONDITIONPARAMS varchar(128) null,
         FKEVENTCONFIG numeric(19,0) not null,
         FKEVENTTYPEDETAIL numeric(19,0) not null,
         primary key (ID),
         unique (FKEVENTCONFIG, RULEORDER)
     );
     create table TB_CA_EVENTPROVIDERS (
         EVENTPROVCLASS varchar(16) not null,
         ID varchar(16) not null,
         NAME varchar(64) not null,
         VERSION int not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         primary key (ID)
     );
     create table TB_CA_EVENTS (
         EVENTCLASS varchar(32) not null,
         ID numeric(19,0) identity not null,
         VERSION int not null,
         ISMANDATORY tinyint not null,
         REFID numeric(19,0) null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         ISDELETED tinyint not null,
         EXPIRATIONDATE datetime null,
         EXECUTIONDATE datetime null,
         SUBSCRIPTIONDATE datetime null,
         OPERATIONALDATE datetime null,
         ISPROVUPDATED tinyint not null,
         ISPROVCANCELLED tinyint not null,
         TRANSMESSKEY varchar(128) null,
         ISENDED tinyint not null,
         ISTRANSMESSERROR tinyint not null,
         TRANSMESSPARM varchar(256) null,
         ISCOMPLETE tinyint null,
         ENTITYOPERATIONALDATE datetime null,
         FKSECURITY numeric(19,0) null,
         FKSTATEFLOW varchar(16) not null,
         FKSTATE varchar(16) not null,
         FKEVENTTYPE varchar(4) not null,
         FKEVENTGROUP numeric(19,0) null,
         FKEVENTDETAIL numeric(19,0) null,
         FKEVENTPROVIDER varchar(16) null,
         FKMASTEREVENT numeric(19,0) null,
         primary key (ID)
     );
     create table TB_CA_EVENTTYPEDETAILS (
         ID numeric(19,0) identity not null,
         NAME varchar(64) not null,
         ISHIDDEN tinyint not null,
         DESCRIPTION varchar(256) null,
         FORMAT varchar(32) null,
         VERSION int not null,
         MAXLENGTH int not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         FIELDPATH varchar(32) not null,
         FIELDTYPE varchar(16) not null,
         ISDISPLAYINTABLE tinyint not null,
         DISPLAYINTABLEORDER int not null,
         DISPLAYGROUP varchar(64) not null,
         DISPLAYGROUPORDER int not null,
         DISPLAYINGROUPORDER int null,
         DISPLAYROW int null,
         DISPLAYCOLUMN int null,
         DISPLAYTOP int null,
         DISPLAYLEFT int null,
         ISEXTENSION tinyint not null,
         ISBASIC tinyint not null,
         EDITABLENORMALIZATION tinyint not null,
         FKEVENTTYPE varchar(4) not null,
         primary key (ID)
     );
     create table TB_CA_EVENTTYPES (
         ID varchar(4) not null,
         NAME varchar(64) not null,
         DESCRIPTION varchar(256) null,
         ISOPTIONAL tinyint not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         primary key (ID)
     );
     create table TB_CA_FORMATPROVIDERS (
         ID numeric(19,0) identity not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         IDATMESSAGE varchar(16) not null,
         FKMESSAGEFORMAT varchar(16) not null,
         FKEVENTPROVIDER varchar(16) not null,
         primary key (ID)
     );
     create table TB_CA_MESSAGEFIELDCONFIGS (
         PATH varchar(128) not null,
         HIDDEN tinyint not null,
         DESCRIPTION varchar(128) null,
         VERSION int not null,
         FIELDNAME varchar(64) not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         FIELDTYPE varchar(32) not null,
         DISPLAYGROUP varchar(64) null,
         DISPLAYROW int not null,
         DISPLAYCOLUMN int not null,
         FIELDLENGTH int null,
         FIELDFORMAT varchar(32) null,
         FKFORMAT varchar(16) not null,
         FKTYPE varchar(16) not null,
         primary key (FKFORMAT, FKTYPE, PATH)
     );
     create table TB_CA_MESSAGEFORMATS (
         ID varchar(16) not null,
         NAME varchar(64) not null,
         PATH varchar(128) not null,
         VERSION int not null,
         PATTERN varchar(64) not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         primary key (ID)
     );
     create table TB_CA_MESSAGETYPES (
         CODE varchar(16) not null,
         NAME varchar(64) not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         FKFORMAT varchar(16) not null,
         primary key (FKFORMAT, CODE)
     );
     create table TB_CA_QUESTIONS (
         ID numeric(19,0) identity not null,
         DEFAULTVALUE varchar(256) null,
         ISHIDDEN tinyint not null,
         DESCRIPTION varchar(256) null,
         TEXT varchar(256) not null,
         FORMAT varchar(64) null,
         VERSION int not null,
         POSITION int not null,
         ISOPTIONAL tinyint not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         HEADERPOSITION int not null,
         ISHEADER tinyint not null,
         HEADERGROUP numeric(19,0) not null,
         FKEVENT numeric(19,0) not null,
         FKANSWERTYPE varchar(16) null,
         primary key (ID)
     );
     create table TB_CONFIGS (
         ID varchar(32) not null,
         TYPEVALUE varchar(15) not null,
         DESCRIPTION varchar(128) null,
         VERSION int not null,
         ISUPDATABLE tinyint not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         BOOLEANVALUE tinyint null,
         LONGVALUE numeric(19,0) null,
         DOUBLEVALUE double precision null,
         TIMESTAMPVALUE datetime null,
         SHORTSTRINGVALUE varchar(16) null,
         MIDDLESTRINGVALUE varchar(64) null,
         LONGSTRINGVALUE varchar(128) null,
         VERYLONGSTRINGVALUE varchar(256) null,
         ISEDITABLE tinyint not null,
         FKAPPLICATION varchar(16) not null,
         primary key (FKAPPLICATION, ID)
     );
     create table TB_CONTAINER (
         NAME varchar(100) not null,
         TYPE varchar(15) not null,
         primary key (NAME, TYPE)
     );
     create table TB_CONTAINER_SCHEDULE (
         SCHEDULE_ID int not null,
         SCHEDULE_NAME varchar(100) null,
         CONTAINER_NAME varchar(100) null,
         CONTAINER_TYPE varchar(15) null,
         PUBLISH_DATE datetime null,
         EXPIRATION_DATE datetime null,
         STATUS tinyint null,
         CONTENT_ID int null,
         URL varchar(200) null,
         ORDER_CONFIG int null,
         primary key (SCHEDULE_ID)
     );
     create table TB_CONTENT (
         CONTENT_ID int not null,
         STATUS_ID int null,
         CREATION_DATE datetime null,
         RELEASE_DATE datetime null,
         DRAFT_DATE datetime null,
         APPROVAL_DATE datetime null,
         PUBLISH_DATE datetime null,
         EXPIRATION_DATE datetime null,
         DRAFT_USER_ID varchar(20) null,
         RELEASE_USER_ID varchar(20) null,
         APPROVE_USER_ID varchar(20) null,
         MODULE_ID varchar(20) null,
         CONTENT_LANGUAGE varchar(2) null,
         MASTER_CONTENT_ID int null,
         primary key (CONTENT_ID)
     );
     create table TB_CONTENTS_CATEGORIES (
         CONTENT_ID int not null,
         CATEGORY_ID int not null,
         primary key (CATEGORY_ID, CONTENT_ID)
     );
     create table TB_CONTENT_PERMISSION (
         CONTENT_ID int not null,
         PRINCIPAL varchar(50) null,
         PRINCIPAL_TYPE varchar(20) null,
         READ_PERMISSION tinyint null,
         WRITE_PERMISSION tinyint null
     );
     create table TB_CONTENT_STATUS (
         STATUS_ID int not null,
         DESCRIPTION varchar(20) null,
         primary key (STATUS_ID)
     );
     create table TB_DIARY (
         CONTENT_ID int not null,
         SUBJECT varchar(100) null,
         MESSAGE_DATE datetime null,
         MESSAGE text null,
         primary key (CONTENT_ID)
     );
     create table TB_DOCUMENTS (
         CONTENT_ID int not null,
         FILE_NAME varchar(100) null,
         SUBJECT varchar(100) null,
         DESCRIPTION varchar(1000) null,
         KEYWORDS varchar(2000) null,
         primary key (CONTENT_ID)
     );
     create table TB_DOCUMENT_VERSIONS (
         CONTENT_ID int not null,
         AUTHOR varchar(100) null,
         VERSION_DATE datetime null,
         SAVED_FILE_NAME varchar(256) null,
         MIME_TYPE varchar(50) null,
         DOCUMENT_ID int null,
         ATTACHED_TO int null,
         VERSION_NUMBER int null,
         primary key (CONTENT_ID)
     );
     create table TB_DOMAINCLUSTERS (
         CODE varchar(32) not null,
         NAME varchar(64) not null,
         VERSION int not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         FKAPPLICATION varchar(16) not null,
         FKDOMAIN varchar(16) not null,
         primary key (FKAPPLICATION, FKDOMAIN, CODE)
     );
     create table TB_DOMAINNORMS (
         CODE varchar(32) not null,
         DESCRIPTION varchar(64) null,
         VERSION int not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         FKAPPLICATION varchar(16) not null,
         FKDOMAIN varchar(16) not null,
         primary key (FKAPPLICATION, FKDOMAIN, CODE)
     );
     create table TB_DOMAINS (
         CODE varchar(16) not null,
         NAME varchar(64) null,
         VERSION int not null,
         ISUPDATABLE tinyint not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         ISDELETABLE tinyint not null,
         ISCACHEABLE tinyint not null,
         FKAPPLICATION varchar(16) not null,
         primary key (FKAPPLICATION, CODE)
     );
     create table TB_DOMAINVALUES (
         ORIGINALVALUE varchar(64) not null,
         VERSION int not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         FKNRMAPPLICATION varchar(16) not null,
         FKNRMDOMAIN varchar(16) not null,
         FKNORM varchar(32) not null,
         FKCLTAPPLICATION varchar(16) not null,
         FKCLTDOMAIN varchar(16) not null,
         FKCLUSTER varchar(32) not null,
         primary key (ORIGINALVALUE, FKNRMAPPLICATION, FKNRMDOMAIN, FKNORM, FKCLTAPPLICATION, FKCLTDOMAIN, FKCLUSTER)
     );
     create table TB_FLOWS (
         ID varchar(16) not null,
         NAME varchar(64) not null,
         DESCRIPTION varchar(128) null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         FKAPPLICATION varchar(16) not null,
         primary key (ID)
     );
     create table TB_FUNCTIONS (
         FUNCTION_ID varchar(30) not null,
         MODULE_ID varchar(30) not null,
         FUNCTION_URI varchar(255) null,
         FUNCTION_TITLE varchar(100) null,
         primary key (FUNCTION_ID, MODULE_ID)
     );
     create table TB_FUNCTION_GROUPS (
         GROUP_ID varchar(30) not null,
         FUNCTION_ID varchar(30) not null,
         MODULE_ID varchar(30) not null,
         primary key (GROUP_ID, FUNCTION_ID, MODULE_ID)
     );
     create table TB_GROUPS (
         GROUP_ID varchar(30) not null,
         GROUP_TYPE int not null,
         DESCRIPTION varchar(255) null,
         GROUP_RULE varchar(255) null,
         primary key (GROUP_ID)
     );
     create table TB_GROUP_TYPES (
         GROUP_TYPE int not null,
         DESCRIPTION varchar(255) null,
         ADMIN_PAGE_URL varchar(255) null,
         primary key (GROUP_TYPE)
     );
     create table TB_HTML (
         CONTENT_ID int not null,
         TITLE varchar(100) null,
         BODY text null,
         primary key (CONTENT_ID)
     );
     create table TB_LOGFIELDUPDATES (
         ID int identity not null,
         NEWVALUE varchar(256) not null,
         OLDVALUE varchar(256) not null,
         FIELDNAME varchar(128) not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         FKLOG numeric(19,0) not null,
         primary key (ID)
     );
     create table TB_LOGS (
         LOGCLASS varchar(32) not null,
         ID numeric(19,0) identity not null,
         MESSAGE varchar(1750) not null,
         LOGTYPE varchar(128) not null,
         LOGLEVEL int not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         FKAPPLICATION varchar(16) not null,
         STACKTRACE text null,
         ENTITYNAME varchar(255) null,
         OPERATIONID numeric(19,0) null,
         FKFLOW varchar(16) null,
         FKTRANSITION varchar(16) null,
         UPDATEDENTITY varchar(128) null,
         primary key (ID)
     );
     create table TB_MENU_ITEMS (
         ITEM_TYPE varchar(31) not null,
         MENU_ITEM_ID varchar(255) not null,
         URL varchar(255) null,
         ENABLED char(1) null,
         MENU_ITEM_TITLE varchar(255) null,
         LANGUAGE_ID varchar(255) null,
         MARKED char(1) null,
         ICON1 varchar(255) null,
         ICON2 varchar(255) null,
         EXTERNAL_LINK tinyint null,
         TARGET_LINK tinyint null,
         CONTAINER_NAME varchar(100) null,
         CONTAINER_TYPE varchar(15) null,
         PARENT varchar(255) null,
         CATEGORY_ID int null,
         CHILD_INDEX int null,
         primary key (MENU_ITEM_ID)
     );
     create table TB_MENU_ITEM_GROUP (
         MENU_ITEM_ID varchar(255) not null,
         GROUP_ID varchar(30) not null,
         primary key (MENU_ITEM_ID, GROUP_ID)
     );
     create table TB_MESSAGE (
         CONTENT_ID int not null,
         SUBJECT varchar(100) null,
         MESSAGE_DATE datetime null,
         MESSAGE text null,
         primary key (CONTENT_ID)
     );
     create table TB_MIME_TYPES (
         MIME_TYPE varchar(50) not null,
         MIME_DESCRIPTION varchar(200) null,
         primary key (MIME_TYPE)
     );
     create table TB_MODULES (
         MODULE_ID varchar(30) not null,
         ADMIN_URI varchar(200) null,
         MODULE_TITLE varchar(100) null,
         CONTENT_LOCATION varchar(100) null,
         DESCRIPTION varchar(200) null,
         ICON1 varchar(250) null,
         ICON2 varchar(250) null,
         MODULE_TYPE int null,
         primary key (MODULE_ID)
     );
     create table TB_MODULE_TYPES (
         MODULE_TYPE int not null,
         DESCRIPTION varchar(200) null,
         primary key (MODULE_TYPE)
     );
     create table TB_NEWS (
         CONTENT_ID int not null,
         TITLE varchar(255) null,
         SUBTITLE varchar(255) null,
         BODY text null,
         primary key (CONTENT_ID)
     );
     create table TB_NEWS_LINK (
         NEWS_ID int not null,
         URL varchar(255) null,
         NEWS_LINK_TITLE varchar(255) null,
         ALT varchar(255) null,
         LINK_ID int not null,
         primary key (NEWS_ID, LINK_ID)
     );
     create table TB_NEWS_PICTURE (
         NEWS_ID int not null,
         URL varchar(255) null,
         ALT varchar(255) null,
         PICTURE_NAME varchar(255) null,
         PICTURE_ID int not null,
         primary key (NEWS_ID, PICTURE_ID)
     );
     create table TB_OLD_PASSWORDS (
         USER_ID varchar(30) not null,
         PASSWORD varchar(30) null,
         PASSWORD_TIMESTAMP datetime null
     );
     create table TB_PAGES (
         SEQUENCE_NUMBER numeric(19,0) not null,
         TRACKING_ID numeric(19,0) not null,
         ACCESS_TIME datetime null,
         PAGE_NAME varchar(80) null,
         REFERRER varchar(80) null,
         primary key (SEQUENCE_NUMBER, TRACKING_ID)
     );
     create table TB_POLL (
         CONTENT_ID int not null,
         DESCRIPTION varchar(100) null,
         QUESTION varchar(200) null,
         QUESTION_TYPE int null,
         primary key (CONTENT_ID)
     );
     create table TB_POLL_USER (
         POLL_ID int not null,
         USER_ID varchar(30) null
     );
     create table TB_POPUP_SCHEDULE (
         SCHEDULE_ID int not null,
         HEIGHT numeric(19,0) null,
         WIDTH numeric(19,0) null,
         LEFT_POS numeric(19,0) null,
         TOP_POS numeric(19,0) null,
         TIMER numeric(19,0) null,
         VIEW_TIMES numeric(19,0) null,
         primary key (SCHEDULE_ID)
     );
     create table TB_RESOURCE_COLLECTION (
         COLLECTION_NAME varchar(20) not null,
         DESCRIPTION varchar(200) null,
         URL_PATTERN varchar(200) null,
         CONSTRAINTS_ENABLED tinyint null,
         primary key (COLLECTION_NAME)
     );
     create table TB_SECURITY_CONSTRAINT (
         COLLECTION_NAME varchar(20) not null,
         GROUP_ID varchar(30) not null,
         primary key (COLLECTION_NAME, GROUP_ID)
     );
     create table TB_SP_CUSTODIAN_ACCOUNTS (
         ID numeric(19,0) identity not null,
         NAME varchar(64) not null,
         VERSION int not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         ACCOUNTNUMBER varchar(64) not null,
         OWNACCOUNT tinyint not null,
         FKPROVIDER varchar(16) null,
         primary key (ID)
     );
     create table TB_SP_CUSTOMERS (
         MANAGERTYPE varchar(16) not null,
         ID numeric(19,0) identity not null,
         NAME varchar(64) not null,
         VERSION int not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         CUSTOMERID varchar(64) null,
         FKCUSTMANAGERGROUP numeric(19,0) null,
         FKCUSTMANAGER numeric(19,0) null,
         primary key (ID)
     );
     create table TB_SP_MANAGERGROUPS (
         ID numeric(19,0) identity not null,
         NAME varchar(64) not null,
         VERSION int not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         CUSTOMERID varchar(64) null,
         primary key (ID)
     );
     create table TB_SP_MANAGERS (
         ID numeric(19,0) identity not null,
         NAME varchar(64) not null,
         VERSION int not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         CUSTOMERID varchar(64) null,
         EMAIL varchar(64) null,
         FKMANAGERGROUP numeric(19,0) null,
         primary key (ID)
     );
     create table TB_SP_MARKETS (
         ID numeric(19,0) identity not null,
         NAME varchar(128) null,
         COUNTRY varchar(2) null,
         VERSION int not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         CUSTOMERID varchar(64) null,
         TICKER varchar(32) null,
         MIC varchar(4) not null,
         CITY varchar(32) null,
         primary key (ID)
     );
     create table TB_SP_PLANNING_PROCESS (
         ID numeric(19,0) identity not null,
         NAME varchar(30) not null,
         VERSION int not null,
         REFID numeric(19,0) null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         ISDELETED tinyint not null,
         CRON varchar(30) not null,
         EXECUTIONDATE datetime null,
         TEMPLATE image null,
         MANUAL tinyint null,
         FKPROCESS numeric(19,0) null,
         primary key (ID)
     );
     create table TB_SP_PROCESS (
         PROCESSTYPE varchar(16) not null,
         ID numeric(19,0) identity not null,
         DESCRIPTION varchar(16) not null,
         VERSION int not null,
         REFID numeric(19,0) null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         ISDELETED tinyint not null,
         FILENAME varchar(64) not null,
         PATH varchar(64) not null,
         FILEEXTENSION varchar(6) not null,
         OVERWRITE tinyint not null,
         FTP_USER varchar(128) null,
         FTP_PASSWORD varchar(128) null,
         FTP_SERVER varchar(128) null,
         FTP_PATH varchar(256) null,
         FTP_FILE varchar(128) null,
         FTP_BINARY tinyint null,
         FTP_PASSIVE_MODE tinyint null,
         FTP_PROXY varchar(128) null,
         FTP_PORT varchar(6) null,
         PROXY_USER varchar(128) null,
         PROXY_PASSWORD varchar(128) null,
         SHELL varchar(256) null,
         primary key (ID)
     );
     create table TB_SP_SECURITIES (
         SECURITYCLASS varchar(16) not null,
         ID numeric(19,0) identity not null,
         NAME varchar(64) not null,
         COUNTRY varchar(2) null,
         VERSION int not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         ISIN varchar(12) null,
         CUSIP varchar(9) null,
         SEDOL varchar(7) null,
         PROVIDER_ID1 varchar(16) null,
         PROVIDER_ID2 varchar(16) null,
         PROVIDER_ID3 varchar(16) null,
         SEC_TYPE varchar(32) null,
         IND_SECTOR varchar(32) null,
         IND_GROUP varchar(32) null,
         IND_SUBGROUP varchar(32) null,
         REL_INDEX varchar(8) null,
         INCUSTPORTFOLIO tinyint not null,
         CURRENCY varchar(3) null,
         CUSTOMERID varchar(64) null,
         TICKER varchar(32) null,
         EXPIRATIONDATE datetime null,
         FKDETAIL numeric(19,0) null,
         FKPLANIFICATION numeric(19,0) null,
         FKMARKET numeric(19,0) null,
         FKFINANCIALASSET varchar(4) null,
         primary key (ID)
     );
     create table TB_SP_SECURITYDETAILS (
         ID numeric(19,0) identity not null,
         VERSION int not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         SHORTSTRING1 varchar(16) null,
         SHORTSTRING2 varchar(16) null,
         SHORTSTRING3 varchar(16) null,
         SHORTSTRING4 varchar(16) null,
         SHORTSTRING5 varchar(16) null,
         SHORTSTRING6 varchar(16) null,
         SHORTSTRING7 varchar(16) null,
         SHORTSTRING8 varchar(16) null,
         SHORTSTRING9 varchar(16) null,
         SHORTSTRING10 varchar(16) null,
         SHORTSTRING11 varchar(16) null,
         SHORTSTRING12 varchar(16) null,
         SHORTSTRING13 varchar(16) null,
         SHORTSTRING14 varchar(16) null,
         SHORTSTRING15 varchar(16) null,
         SHORTSTRING16 varchar(16) null,
         SHORTSTRING17 varchar(16) null,
         SHORTSTRING18 varchar(16) null,
         SHORTSTRING19 varchar(16) null,
         SHORTSTRING20 varchar(16) null,
         SHORTSTRING21 varchar(16) null,
         SHORTSTRING22 varchar(16) null,
         SHORTSTRING23 varchar(16) null,
         SHORTSTRING24 varchar(16) null,
         SHORTSTRING25 varchar(16) null,
         SHORTSTRING26 varchar(16) null,
         SHORTSTRING27 varchar(16) null,
         SHORTSTRING28 varchar(16) null,
         SHORTSTRING29 varchar(16) null,
         SHORTSTRING30 varchar(16) null,
         SHORTSTRING31 varchar(16) null,
         SHORTSTRING32 varchar(16) null,
         SHORTSTRING33 varchar(16) null,
         SHORTSTRING34 varchar(16) null,
         SHORTSTRING35 varchar(16) null,
         SHORTSTRING36 varchar(16) null,
         SHORTSTRING37 varchar(16) null,
         SHORTSTRING38 varchar(16) null,
         SHORTSTRING39 varchar(16) null,
         SHORTSTRING40 varchar(16) null,
         MIDDLESTRING1 varchar(32) null,
         MIDDLESTRING2 varchar(32) null,
         MIDDLESTRING3 varchar(32) null,
         MIDDLESTRING4 varchar(32) null,
         MIDDLESTRING5 varchar(32) null,
         MIDDLESTRING6 varchar(32) null,
         MIDDLESTRING7 varchar(32) null,
         MIDDLESTRING8 varchar(32) null,
         MIDDLESTRING9 varchar(32) null,
         MIDDLESTRING10 varchar(32) null,
         MIDDLESTRING11 varchar(32) null,
         MIDDLESTRING12 varchar(32) null,
         MIDDLESTRING13 varchar(32) null,
         MIDDLESTRING14 varchar(32) null,
         MIDDLESTRING15 varchar(32) null,
         MIDDLESTRING16 varchar(32) null,
         MIDDLESTRING17 varchar(32) null,
         MIDDLESTRING18 varchar(32) null,
         MIDDLESTRING19 varchar(32) null,
         MIDDLESTRING20 varchar(32) null,
         MIDDLESTRING21 varchar(32) null,
         MIDDLESTRING22 varchar(32) null,
         MIDDLESTRING23 varchar(32) null,
         MIDDLESTRING24 varchar(32) null,
         MIDDLESTRING25 varchar(32) null,
         MIDDLESTRING26 varchar(32) null,
         MIDDLESTRING27 varchar(32) null,
         MIDDLESTRING28 varchar(32) null,
         MIDDLESTRING29 varchar(32) null,
         MIDDLESTRING30 varchar(32) null,
         MIDDLESTRING31 varchar(32) null,
         MIDDLESTRING32 varchar(32) null,
         MIDDLESTRING33 varchar(32) null,
         MIDDLESTRING34 varchar(32) null,
         MIDDLESTRING35 varchar(32) null,
         MIDDLESTRING36 varchar(32) null,
         MIDDLESTRING37 varchar(32) null,
         MIDDLESTRING38 varchar(32) null,
         MIDDLESTRING39 varchar(32) null,
         MIDDLESTRING40 varchar(32) null,
         LONGSTRING1 varchar(64) null,
         LONGSTRING2 varchar(64) null,
         LONGSTRING3 varchar(64) null,
         LONGSTRING4 varchar(64) null,
         LONGSTRING5 varchar(64) null,
         LONGSTRING6 varchar(64) null,
         LONGSTRING7 varchar(64) null,
         LONGSTRING8 varchar(64) null,
         LONGSTRING9 varchar(64) null,
         LONGSTRING10 varchar(64) null,
         LONGSTRING11 varchar(64) null,
         LONGSTRING12 varchar(64) null,
         LONGSTRING13 varchar(64) null,
         LONGSTRING14 varchar(64) null,
         LONGSTRING15 varchar(64) null,
         LONGSTRING16 varchar(64) null,
         LONGSTRING17 varchar(64) null,
         LONGSTRING18 varchar(64) null,
         LONGSTRING19 varchar(64) null,
         LONGSTRING20 varchar(64) null,
         LONGSTRING21 varchar(64) null,
         LONGSTRING22 varchar(64) null,
         LONGSTRING23 varchar(64) null,
         LONGSTRING24 varchar(64) null,
         LONGSTRING25 varchar(64) null,
         LONGSTRING26 varchar(64) null,
         LONGSTRING27 varchar(64) null,
         LONGSTRING28 varchar(64) null,
         LONGSTRING29 varchar(64) null,
         LONGSTRING30 varchar(64) null,
         LONGSTRING31 varchar(64) null,
         LONGSTRING32 varchar(64) null,
         LONGSTRING33 varchar(64) null,
         LONGSTRING34 varchar(64) null,
         LONGSTRING35 varchar(64) null,
         LONGSTRING36 varchar(64) null,
         LONGSTRING37 varchar(64) null,
         LONGSTRING38 varchar(64) null,
         LONGSTRING39 varchar(64) null,
         LONGSTRING40 varchar(64) null,
         VERYLONGSTRING1 varchar(128) null,
         VERYLONGSTRING2 varchar(128) null,
         VERYLONGSTRING3 varchar(128) null,
         VERYLONGSTRING4 varchar(128) null,
         VERYLONGSTRING5 varchar(128) null,
         VERYLONGSTRING6 varchar(128) null,
         VERYLONGSTRING7 varchar(128) null,
         VERYLONGSTRING8 varchar(128) null,
         VERYLONGSTRING9 varchar(128) null,
         VERYLONGSTRING10 varchar(128) null,
         VERYLONGSTRING11 varchar(128) null,
         VERYLONGSTRING12 varchar(128) null,
         VERYLONGSTRING13 varchar(128) null,
         VERYLONGSTRING14 varchar(128) null,
         VERYLONGSTRING15 varchar(128) null,
         VERYLONGSTRING16 varchar(128) null,
         VERYLONGSTRING17 varchar(128) null,
         VERYLONGSTRING18 varchar(128) null,
         VERYLONGSTRING19 varchar(128) null,
         VERYLONGSTRING20 varchar(128) null,
         VERYLONGSTRING21 varchar(128) null,
         VERYLONGSTRING22 varchar(128) null,
         VERYLONGSTRING23 varchar(128) null,
         VERYLONGSTRING24 varchar(128) null,
         VERYLONGSTRING25 varchar(128) null,
         VERYLONGSTRING26 varchar(128) null,
         VERYLONGSTRING27 varchar(128) null,
         VERYLONGSTRING28 varchar(128) null,
         VERYLONGSTRING29 varchar(128) null,
         VERYLONGSTRING30 varchar(128) null,
         VERYLONGSTRING31 varchar(128) null,
         VERYLONGSTRING32 varchar(128) null,
         VERYLONGSTRING33 varchar(128) null,
         VERYLONGSTRING34 varchar(128) null,
         VERYLONGSTRING35 varchar(128) null,
         VERYLONGSTRING36 varchar(128) null,
         VERYLONGSTRING37 varchar(128) null,
         VERYLONGSTRING38 varchar(128) null,
         VERYLONGSTRING39 varchar(128) null,
         VERYLONGSTRING40 varchar(128) null,
         primary key (ID)
     );
     create table TB_SP_SECURITYPORTFOLIO (
         ID numeric(19,0) identity not null,
         VERSION int not null,
         REFID numeric(19,0) null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         ISDELETED tinyint not null,
         CUSTOMERAMOUNT numeric(19,0) null,
         CUSTODIANAMOUNT numeric(19,0) null,
         EMISSIONDATE datetime not null,
         FKPROVIDERACCOUNT numeric(19,0) null,
         FKCUSTOMER numeric(19,0) null,
         FKSECURITY numeric(19,0) not null,
         primary key (ID)
     );
     create table TB_SP_SECURITY_FASSETS (
         ID varchar(4) not null,
         NAME varchar(64) null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         primary key (ID)
     );
     create table TB_SP_SECURITY_FASSETS_DETAILS (
         ID numeric(19,0) identity not null,
         NAME varchar(64) not null,
         ISHIDDEN tinyint not null,
         DESCRIPTION varchar(256) null,
         FORMAT varchar(32) null,
         VERSION int not null,
         MAXLENGTH int not null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         UPDUSER varchar(128) not null,
         UPDDATE datetime not null,
         ISDELETED tinyint not null,
         FIELDPATH varchar(32) not null,
         FIELDTYPE varchar(16) not null,
         ISDISPLAYINTABLE tinyint not null,
         DISPLAYINTABLEORDER int not null,
         DISPLAYGROUP varchar(64) not null,
         DISPLAYGROUPORDER int not null,
         DISPLAYINGROUPORDER int null,
         DISPLAYROW int null,
         DISPLAYCOLUMN int null,
         DISPLAYTOP int null,
         DISPLAYLEFT int null,
         ISEXTENSION tinyint not null,
         ISSTORE tinyint not null,
         ISBASIC tinyint not null,
         FKSECURITYFA varchar(4) not null,
         primary key (ID)
     );
     create table TB_SP_SECURITY_PLANNING (
         ID numeric(19,0) identity not null,
         FKSECURITY numeric(19,0) null,
         FKPLANNING numeric(19,0) null,
         primary key (ID)
     );
     create table TB_STATES (
         CODE varchar(16) not null,
         NAME varchar(64) not null,
         DESCRIPTION varchar(128) null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         ISINITIAL tinyint not null,
         ISEND tinyint not null,
         FKFLOW varchar(16) not null,
         primary key (FKFLOW, CODE)
     );
     create table TB_TRACKING (
         TRACKING_ID numeric(19,0) not null,
         USER_ID varchar(20) null,
         SESSION_ID varchar(255) null,
         SESSION_START datetime null,
         SESSION_END datetime null,
         UNIQUE_IP varchar(15) null,
         PLATFORM varchar(50) null,
         SCREEN_SIZE varchar(15) null,
         BROWSER varchar(50) null,
         USER_AGENT varchar(150) null,
         BROWSER_NAME varchar(30) null,
         BROWSER_VERSION varchar(5) null,
         primary key (TRACKING_ID)
     );
     create table TB_TRANSITIONS (
         CODE varchar(16) not null,
         NAME varchar(64) not null,
         DESCRIPTION varchar(128) null,
         CRTUSER varchar(128) not null,
         CRTDATE datetime not null,
         TRANSITIONMESSAGE varchar(128) null,
         TRANSITIONEXECLASS varchar(128) null,
         FKFLOW varchar(16) not null,
         FKENDSTATEFLOW varchar(16) not null,
         FKENDSTATE varchar(16) not null,
         FKINITIALSTATEFLOW varchar(16) null,
         FKINITIALSTATE varchar(16) null,
         primary key (FKFLOW, CODE)
     );
     create table TB_USER_GROUP (
         USER_ID varchar(30) not null,
         GROUP_ID varchar(30) null
     );
     create table TB_USER_PROFILES (
         USER_ID varchar(30) not null,
         USER_TYPE varchar(20) not null,
         PASSWORD varchar(30) null,
         PASSWORD_EXPIRATION_DATE datetime null,
         FIRSTNAME varchar(50) null,
         LASTNAME varchar(50) null,
         LANGUAGE varchar(2) null,
         COUNTRY varchar(2) null,
         EMAIL varchar(50) null,
         GENDER varchar(1) null,
         BIRTHDATE datetime null,
         HOMEPAGE varchar(255) null,
         EXPIRATION_DATE datetime null,
         ADDRESS varchar(255) null,
         CITY varchar(50) null,
         PROV varchar(20) null,
         ZIPCODE varchar(20) null,
         STATE varchar(20) null,
         PHONE varchar(20) null,
         MOBILE varchar(20) null,
         FAX varchar(20) null,
         ID_USER_STATUS int null,
         primary key (USER_ID)
     );
     create table TB_USER_TYPE (
         TYPE varchar(20) not null,
         CLASSNAME varchar(100) null,
         primary key (TYPE)
     );
     create index IDX_HSEVDETAIL_DELETED on HS_CA_EVENTDETAILS (ISDELETED);
     create index IDX_HSEVDETAIL_VERSION on HS_CA_EVENTDETAILS (VERSION);
     create index IDX_HSEVDETAIL_REFID on HS_CA_EVENTDETAILS (REFID);
     create index IDX_HSEVDETAIL_OPERDATE on HS_CA_EVENTDETAILS (OPERATIONALDATE);
     create index IDX_HSEXTDETAIL_FKEVENTDETAIL on HS_CA_EVENTEXTDETAILS (FKEVENTDETAIL);
     create index IDX_HSEVTEXTDETAIL_VERSION on HS_CA_EVENTEXTDETAILS (VERSION);
     create index IDX_HSEVTEXTDETAIL_EXTENSIONID on HS_CA_EVENTEXTDETAILS (EXTENSIONNAME, EXTENSIONNUMBER);
     create index IDX_HSEVTEXTDETAIL_REFID on HS_CA_EVENTEXTDETAILS (REFID);
     create index IDX_HSEVTEXTDETAIL_DELETED on HS_CA_EVENTEXTDETAILS (ISDELETED);
     alter table HS_CA_EVENTEXTDETAILS 
         add constraint FK_HSEXTENDED_DETAIL 
         foreign key (FKEVENTDETAIL) 
         references HS_CA_EVENTDETAILS;
     create index IDX_HSEVMESGFLD_FKEVENTMESSAGE on HS_CA_EVENTMESSAGEFIELDS (FKHSEVENTMESSAGE);
     alter table HS_CA_EVENTMESSAGEFIELDS 
         add constraint FK_HSEVENTFIELD_MESSAGE 
         foreign key (FKHSEVENTMESSAGE) 
         references HS_CA_EVENTMESSAGES;
     create index IDX_HSEVMESSAGE_PROVIDER on HS_CA_EVENTMESSAGES (FKPROVIDER);
     create index IDX_HSEVMESSAGE_FKEVENT on HS_CA_EVENTMESSAGES (FKEVENT);
     create index IDX_HSEVMESSAGE_FKFORMAT on HS_CA_EVENTMESSAGES (FKFORMAT);
     create index IDX_HSEVMESSAGE_MESSAGEID on HS_CA_EVENTMESSAGES (MESSAGEID);
     create index IDX_HSEVMESSAGE_ISINPORTFOLIO on HS_CA_EVENTMESSAGES (ISINPORTFOLIO);
     create index IDX_HSEVMESSAGE_SENDTIMESTAMP on HS_CA_EVENTMESSAGES (SENDERTIMESTAMP);
     create index IDX_HSEVMESSAGE_CRTDATE on HS_CA_EVENTMESSAGES (CRTDATE);
     create index IDX_HSEVMESSAGE_FKSECURITY on HS_CA_EVENTMESSAGES (FKSECURITY);
     create index IDX_HSEVMESSAGE_ISEXTENSION on HS_CA_EVENTMESSAGES (ISEXTENSION);
     create index IDX_HSEVMESSAGE_STATUS on HS_CA_EVENTMESSAGES (ISENDED, FKSTATE);
     create index IDX_HSEVMESSAGE_EFFECTIVEDATE on HS_CA_EVENTMESSAGES (EFFECTIVEDATE);
     create index IDX_HSEVMESSAGE_DELETED on HS_CA_EVENTMESSAGES (ISDELETED);
     create index IDX_HSEVMESSAGE_FKEVENTTYPE on HS_CA_EVENTMESSAGES (FKEVENTTYPE);
     create index IDX_HSEVMESSAGE_OPERATIONNORM on HS_CA_EVENTMESSAGES (OPERATIONNORM);
     alter table HS_CA_EVENTMESSAGES 
         add constraint FK_HSEVENTMESSAGE_EVENTTYPE 
         foreign key (FKEVENTTYPE) 
         references TB_CA_EVENTTYPES;
     alter table HS_CA_EVENTMESSAGES 
         add constraint FK_HSEVENTMESSAGE_SECURITY 
         foreign key (FKSECURITY) 
         references TB_SP_SECURITIES;
     alter table HS_CA_EVENTMESSAGES 
         add constraint FK56046059FC4CA9B4 
         foreign key (FKSTATEFLOW, FKSTATE) 
         references TB_STATES;
     alter table HS_CA_EVENTMESSAGES 
         add constraint FK_HSEVENTMESSAGE_PROVIDER 
         foreign key (FKPROVIDER) 
         references TB_CA_EVENTPROVIDERS;
     alter table HS_CA_EVENTMESSAGES 
         add constraint FK_HSEVENTMESSAGE_TYPE 
         foreign key (FKFORMAT, FKTYPE) 
         references TB_CA_MESSAGETYPES;
     create index IDX_HSEVENT_EVENTCLASS on HS_CA_EVENTS (EVENTCLASS);
     create index IDX_HSEVENT_REFID on HS_CA_EVENTS (REFID);
     create index IDX_HSEVENT_VERSION on HS_CA_EVENTS (VERSION);
     create index IDX_HSEVENT_FKEVENTDETAIL on HS_CA_EVENTS (FKEVENTDETAIL);
     create index IDX_HSEVENT_SECURITY on HS_CA_EVENTS (FKSECURITY);
     create index IDX_HSEVENT_STATUS on HS_CA_EVENTS (ISENDED, FKSTATEFLOW, FKSTATE);
     create index IDX_HSEVENT_FKMASTEREVENT on HS_CA_EVENTS (FKMASTEREVENT);
     create index IDX_HSEVENT_OPERDATE on HS_CA_EVENTS (OPERATIONALDATE);
     create index IDX_HSEVENT_DELETED on HS_CA_EVENTS (ISDELETED);
     create index IDX_HSEVENT_FKEVENTPROVIDER on HS_CA_EVENTS (FKEVENTPROVIDER);
     create index IDX_HSEVENT_FKEVENTGROUP on HS_CA_EVENTS (FKEVENTGROUP);
     create index IDX_HSEVENT_EVENTTYPE on HS_CA_EVENTS (FKEVENTTYPE);
     alter table HS_CA_EVENTS 
         add constraint FK_HSEVENT_EVENTTYPE 
         foreign key (FKEVENTTYPE) 
         references TB_CA_EVENTTYPES;
     alter table HS_CA_EVENTS 
         add constraint FK_HSCOLLECTED_GROUP 
         foreign key (FKEVENTGROUP) 
         references HS_CA_EVENTS;
     alter table HS_CA_EVENTS 
         add constraint FK_HSEVENT_SECURITY 
         foreign key (FKSECURITY) 
         references TB_SP_SECURITIES;
     alter table HS_CA_EVENTS 
         add constraint FK617DB5E6FC4CA9B4 
         foreign key (FKSTATEFLOW, FKSTATE) 
         references TB_STATES;
     alter table HS_CA_EVENTS 
         add constraint FK_HSEVENT_PROVIDER 
         foreign key (FKEVENTPROVIDER) 
         references TB_CA_EVENTPROVIDERS;
     alter table HS_CA_EVENTS 
         add constraint FK_HSEVENT_EVENTDETAIL 
         foreign key (FKEVENTDETAIL) 
         references HS_CA_EVENTDETAILS;
     alter table HS_CA_EVENTS 
         add constraint FK_HSGROUP_COLLECTED 
         foreign key (FKMASTEREVENT) 
         references HS_CA_EVENTS;
     create index IDX_HSSECPORT_DELETED on HS_SP_SECURITYPORTFOLIO (ISDELETED);
     alter table HS_SP_SECURITYPORTFOLIO 
         add constraint FK_HS_SECPORT_SECURITY 
         foreign key (FKSECURITY) 
         references TB_SP_SECURITIES;
     alter table HS_SP_SECURITYPORTFOLIO 
         add constraint FK_HS_SECPORT_CUSTOMER 
         foreign key (FKCUSTOMER) 
         references TB_SP_CUSTOMERS;
     alter table TB_ANSWERS 
         add constraint FK_POLL_ANSWER 
         foreign key (POLL_ID) 
         references TB_POLL;
     alter table TB_CATEGORIES 
         add constraint FKE9DA4C8D6F263114 
         foreign key (MASTER_CONTENT_ID) 
         references TB_CONTENT;
     alter table TB_CATEGORIES 
         add constraint FK_CATEGORY_PARENT 
         foreign key (PARENT_ID) 
         references TB_CATEGORIES;
     alter table TB_CATEGORY_PERMISSION 
         add constraint FK_CATEGORY_PERMISSION 
         foreign key (CATEGORY_ID) 
         references TB_CATEGORIES;
     alter table TB_CA_DOMAINCLUSTERFORMATS 
         add constraint FK_DOMCLSFORM_FORMAT 
         foreign key (FKMESSAGEFORMAT) 
         references TB_CA_MESSAGEFORMATS;
     alter table TB_CA_DOMAINCLUSTERFORMATS 
         add constraint FK_DOMCLSFORM_CLUSTER 
         foreign key (FKAPPLICATION, FKDOMAIN, FKDOMAINCLUSTER) 
         references TB_DOMAINCLUSTERS;
     create index IDX_EVCONFFLD_FKEVTYPEDET on TB_CA_EVENTCONFIGFIELDS (FKEVENTTYPEDETAIL);
     create index IDX_EVCONFFLD_FKEVCONF on TB_CA_EVENTCONFIGFIELDS (FKEVENTCONFIG);
     create index IDX_EVCONFFLD_DELETED on TB_CA_EVENTCONFIGFIELDS (ISDELETED);
     alter table TB_CA_EVENTCONFIGFIELDS 
         add constraint FK_EVNCONFFIELD_EVENTCONFIG 
         foreign key (FKEVENTCONFIG) 
         references TB_CA_EVENTCONFIGS;
     alter table TB_CA_EVENTCONFIGFIELDS 
         add constraint FK_EVNCONFFIELD_EVNTYPEDET 
         foreign key (FKEVENTTYPEDETAIL) 
         references TB_CA_EVENTTYPEDETAILS;
     create index IDX_EVCONFMSGFLD_FKEVFILDCONF on TB_CA_EVENTCONFIGMSGFLDS (FKEVENTFIELDCONFIG);
     create index IDX_EVCONFMSGFLD_DELETED on TB_CA_EVENTCONFIGMSGFLDS (ISDELETED);
     create index IDX_EVCONFMSGFLD_FKMESSAGETYPE on TB_CA_EVENTCONFIGMSGFLDS (FKFORMAT, FKMSGTYPE);
     create index IDX_EVCONFMSGFLD_FKEVPROVIDER on TB_CA_EVENTCONFIGMSGFLDS (FKEVENTPROVIDER);
     alter table TB_CA_EVENTCONFIGMSGFLDS 
         add constraint FK_EVNCONFFLDMT_EVENTMESG 
         foreign key (FKFORMAT, FKMSGTYPE) 
         references TB_CA_MESSAGETYPES;
     alter table TB_CA_EVENTCONFIGMSGFLDS 
         add constraint FK_EVNCONFFLDPRV_EVENTPROV 
         foreign key (FKEVENTPROVIDER) 
         references TB_CA_EVENTPROVIDERS;
     alter table TB_CA_EVENTCONFIGMSGFLDS 
         add constraint FK_EVNCONFFLDPRV_EVNCONFFLD 
         foreign key (FKEVENTFIELDCONFIG) 
         references TB_CA_EVENTCONFIGFIELDS;
     create index IDX_EVCONFIG_DELETED on TB_CA_EVENTCONFIGS (ISDELETED);
     alter table TB_CA_EVENTCONFIGS 
         add constraint FK_EVENTCONFIG_EVENTTYPE 
         foreign key (FKEVENTTYPE) 
         references TB_CA_EVENTTYPES;
     alter table TB_CA_EVENTCONFIGS 
         add constraint FK_EVENTCONFIG_EVENTPROVIDER2 
         foreign key (FKSECPREFPROV2) 
         references TB_CA_EVENTPROVIDERS;
     alter table TB_CA_EVENTCONFIGS 
         add constraint FK_EVENTCONFIG_EVENTPROVIDER1 
         foreign key (FKPREFPROVIDER1) 
         references TB_CA_EVENTPROVIDERS;
     create index IDX_EVDETAIL_VERSION on TB_CA_EVENTDETAILS (VERSION);
     create index IDX_EVDETAIL_OPERDATE on TB_CA_EVENTDETAILS (OPERATIONALDATE);
     create index IDX_EVDETAIL_DELETED on TB_CA_EVENTDETAILS (ISDELETED);
     create index IDX_EVDETAIL_REFID on TB_CA_EVENTDETAILS (REFID);
     create index IDX_EVTEXTDETAIL_REFID on TB_CA_EVENTEXTDETAILS (REFID);
     create index IDX_EVTEXTDETAIL_VERSION on TB_CA_EVENTEXTDETAILS (VERSION);
     create index IDX_EVTEXTDETAIL_FKEVENTDETAIL on TB_CA_EVENTEXTDETAILS (FKEVENTDETAIL);
     create index IDX_EVTEXTDETAIL_DELETED on TB_CA_EVENTEXTDETAILS (ISDELETED);
     create index IDX_EVTEXTDETAIL_EXTENSIONID on TB_CA_EVENTEXTDETAILS (EXTENSIONNAME, EXTENSIONNUMBER);
     alter table TB_CA_EVENTEXTDETAILS 
         add constraint FK_EXTENDED_DETAIL 
         foreign key (FKEVENTDETAIL) 
         references TB_CA_EVENTDETAILS;
     create index IDX_EVHOLANSW_VERSION on TB_CA_EVENTHOLDINGANSWER (VERSION);
     create index IDX_EVHOLANSW_DELETED on TB_CA_EVENTHOLDINGANSWER (ISDELETED);
     alter table TB_CA_EVENTHOLDINGANSWER 
         add constraint FK_QUESTION 
         foreign key (FKQUESTION) 
         references TB_CA_QUESTIONS;
     alter table TB_CA_EVENTHOLDINGANSWER 
         add constraint FK_HOLDING 
         foreign key (FKHOLDING) 
         references TB_SP_SECURITYPORTFOLIO;
     create index IDX_EVMESGFLD_FKEVENTMESSAGE on TB_CA_EVENTMESSAGEFIELDS (FKEVENTMESSAGE);
     alter table TB_CA_EVENTMESSAGEFIELDS 
         add constraint FK_EVENTFIELD_MESSAGE 
         foreign key (FKEVENTMESSAGE) 
         references TB_CA_EVENTMESSAGES;
     create index IDX_EVMESSAGE_MESSAGEID on TB_CA_EVENTMESSAGES (MESSAGEID);
     create index IDX_EVMESSAGE_DELETED on TB_CA_EVENTMESSAGES (ISDELETED);
     create index IDX_EVMESSAGE_FKEVENTTYPE on TB_CA_EVENTMESSAGES (FKEVENTTYPE);
     create index IDX_EVMESSAGE_OPERATIONNORM on TB_CA_EVENTMESSAGES (OPERATIONNORM);
     create index IDX_EVMESSAGE_FKEVENT on TB_CA_EVENTMESSAGES (FKEVENT);
     create index IDX_EVMESSAGE_SENDTIMESTAMP on TB_CA_EVENTMESSAGES (SENDERTIMESTAMP);
     create index IDX_EVMESSAGE_FKSECURITY on TB_CA_EVENTMESSAGES (FKSECURITY);
     create index IDX_EVMESSAGE_EXTENSIONREF on TB_CA_EVENTMESSAGES (EXTENSIONREF);
     create index IDX_EVMESSAGE_ISINPORTFOLIO on TB_CA_EVENTMESSAGES (ISINPORTFOLIO);
     create index IDX_EVMESSAGE_STATUS on TB_CA_EVENTMESSAGES (ISENDED, FKSTATE);
     create index IDX_EVMESSAGE_PROVIDER on TB_CA_EVENTMESSAGES (FKPROVIDER);
     create index IDX_EVMESSAGE_EFFECTIVEDATE on TB_CA_EVENTMESSAGES (EFFECTIVEDATE);
     create index IDX_EVMESSAGE_FKFORMAT on TB_CA_EVENTMESSAGES (FKFORMAT);
     create index IDX_EVMESSAGE_CRTDATE on TB_CA_EVENTMESSAGES (CRTDATE);
     create index IDX_EVMESSAGE_ISEXTENSION on TB_CA_EVENTMESSAGES (ISEXTENSION);
     alter table TB_CA_EVENTMESSAGES 
         add constraint FK_EVENTMESSAGE_EVENTTYPE 
         foreign key (FKEVENTTYPE) 
         references TB_CA_EVENTTYPES;
     alter table TB_CA_EVENTMESSAGES 
         add constraint FK_EVENTMESSAGE_SECURITY 
         foreign key (FKSECURITY) 
         references TB_SP_SECURITIES;
     alter table TB_CA_EVENTMESSAGES 
         add constraint FKEC50F156FC4CA9B4 
         foreign key (FKSTATEFLOW, FKSTATE) 
         references TB_STATES;
     alter table TB_CA_EVENTMESSAGES 
         add constraint FK_EVENTMESSAGE_PROVIDER 
         foreign key (FKPROVIDER) 
         references TB_CA_EVENTPROVIDERS;
     alter table TB_CA_EVENTMESSAGES 
         add constraint FK_EVENTMESSAGE_TYPE 
         foreign key (FKFORMAT, FKTYPE) 
         references TB_CA_MESSAGETYPES;
     alter table TB_CA_EVENTMRCONFIGS 
         add constraint FK_EVENTMRCONFIGS_EVENTCONFIG 
         foreign key (FKEVENTCONFIG) 
         references TB_CA_EVENTCONFIGS;
     alter table TB_CA_EVENTMRCONFIGS 
         add constraint FK_EVENTMRCONFIGS_EVNTYPEDET 
         foreign key (FKEVENTTYPEDETAIL) 
         references TB_CA_EVENTTYPEDETAILS;
     create index IDX_EVPROVIDER_DELETED on TB_CA_EVENTPROVIDERS (ISDELETED);
     create index IDX_EVPROVIDER_EVENTPROVCLASS on TB_CA_EVENTPROVIDERS (EVENTPROVCLASS);
     create index IDX_EVENT_STATUS on TB_CA_EVENTS (ISENDED, FKSTATEFLOW, FKSTATE);
     create index IDX_EVENT_OPERDATE on TB_CA_EVENTS (OPERATIONALDATE);
     create index IDX_EVENT_DELETED on TB_CA_EVENTS (ISDELETED);
     create index IDX_EVENT_FKMASTEREVENT on TB_CA_EVENTS (FKMASTEREVENT);
     create index IDX_EVENT_FKEVENTGROUP on TB_CA_EVENTS (FKEVENTGROUP);
     create index IDX_EVENT_SECURITY on TB_CA_EVENTS (FKSECURITY);
     create index IDX_EVENT_EVENTCLASS on TB_CA_EVENTS (EVENTCLASS);
     create index IDX_EVENT_REFID on TB_CA_EVENTS (REFID);
     create index IDX_EVENT_FKEVENTDETAIL on TB_CA_EVENTS (FKEVENTDETAIL);
     create index IDX_EVENT_FKEVENTPROVIDER on TB_CA_EVENTS (FKEVENTPROVIDER);
     create index IDX_EVENT_EVENTTYPE on TB_CA_EVENTS (FKEVENTTYPE);
     create index IDX_EVENT_VERSION on TB_CA_EVENTS (VERSION);
     alter table TB_CA_EVENTS 
         add constraint FK_EVENT_EVENTTYPE 
         foreign key (FKEVENTTYPE) 
         references TB_CA_EVENTTYPES;
     alter table TB_CA_EVENTS 
         add constraint FK_COLLECTED_GROUP 
         foreign key (FKEVENTGROUP) 
         references TB_CA_EVENTS;
     alter table TB_CA_EVENTS 
         add constraint FK_EVENT_SECURITY 
         foreign key (FKSECURITY) 
         references TB_SP_SECURITIES;
     alter table TB_CA_EVENTS 
         add constraint FKDAA19789FC4CA9B4 
         foreign key (FKSTATEFLOW, FKSTATE) 
         references TB_STATES;
     alter table TB_CA_EVENTS 
         add constraint FK_EVENT_PROVIDER 
         foreign key (FKEVENTPROVIDER) 
         references TB_CA_EVENTPROVIDERS;
     alter table TB_CA_EVENTS 
         add constraint FK_EVENT_EVENTDETAIL 
         foreign key (FKEVENTDETAIL) 
         references TB_CA_EVENTDETAILS;
     alter table TB_CA_EVENTS 
         add constraint FK_GROUP_COLLECTED 
         foreign key (FKMASTEREVENT) 
         references TB_CA_EVENTS;
     create index IDX_EVTYPEDET_DELETED on TB_CA_EVENTTYPEDETAILS (ISDELETED);
     create index IDX_EVTYPEDET_ISDISPLAYINTABLE on TB_CA_EVENTTYPEDETAILS (ISDISPLAYINTABLE);
     create index IDX_EVTYPEDET_FIELDPATH on TB_CA_EVENTTYPEDETAILS (FIELDPATH);
     create index IDX_EVTYPEDET_FKEVENTTYPE on TB_CA_EVENTTYPEDETAILS (FKEVENTTYPE);
     alter table TB_CA_EVENTTYPEDETAILS 
         add constraint FK_DETAIL_EVENTTYPE 
         foreign key (FKEVENTTYPE) 
         references TB_CA_EVENTTYPES;
     create index IDX_FORMATPROV_FKPROVIDER on TB_CA_FORMATPROVIDERS (FKEVENTPROVIDER);
     create index IDX_FORMATPROV_FKFORMAT on TB_CA_FORMATPROVIDERS (FKMESSAGEFORMAT, IDATMESSAGE);
     alter table TB_CA_FORMATPROVIDERS 
         add constraint FK_FORMATPROV_FORMAT 
         foreign key (FKMESSAGEFORMAT) 
         references TB_CA_MESSAGEFORMATS;
     alter table TB_CA_FORMATPROVIDERS 
         add constraint FK_FORMATPROV_PROVIDER 
         foreign key (FKEVENTPROVIDER) 
         references TB_CA_EVENTPROVIDERS;
     alter table TB_CA_MESSAGEFIELDCONFIGS 
         add constraint FK_FIELD_TYPE 
         foreign key (FKFORMAT, FKTYPE) 
         references TB_CA_MESSAGETYPES;
     create index IDX_MESGFORMAT_DELETED on TB_CA_MESSAGEFORMATS (ISDELETED);
     alter table TB_CA_MESSAGETYPES 
         add constraint FK_TYPE_FORMAT 
         foreign key (FKFORMAT) 
         references TB_CA_MESSAGEFORMATS;
     create index IDX_QUESTION_VERSION on TB_CA_QUESTIONS (VERSION);
     create index IDX_QUESTION_DELETED on TB_CA_QUESTIONS (ISDELETED);
     create index IDX_QUESTION_EVENT on TB_CA_QUESTIONS (FKEVENT);
     alter table TB_CA_QUESTIONS 
         add constraint FK_ANSWERTYPE 
         foreign key (FKANSWERTYPE) 
         references TB_CA_ANSWERTYPES;
     alter table TB_CA_QUESTIONS 
         add constraint FK_QUESTIONEVENT 
         foreign key (FKEVENT) 
         references TB_CA_EVENTS;
     alter table TB_CONFIGS 
         add constraint FK_CONFIG_APPLICATION 
         foreign key (FKAPPLICATION) 
         references TB_APPLICATIONS;
     alter table TB_CONTAINER_SCHEDULE 
         add constraint FK_SCHEDULE_HTML 
         foreign key (CONTENT_ID) 
         references TB_CONTENT;
     alter table TB_CONTAINER_SCHEDULE 
         add constraint FK_CONTAINER_SCHEDULE 
         foreign key (CONTAINER_NAME, CONTAINER_TYPE) 
         references TB_CONTAINER;
     alter table TB_CONTENT 
         add constraint FK_MASTER_CONTENT 
         foreign key (MASTER_CONTENT_ID) 
         references TB_CONTENT;
     alter table TB_CONTENT 
         add constraint FK_CONTENT_STATUS 
         foreign key (STATUS_ID) 
         references TB_CONTENT_STATUS;
     alter table TB_CONTENTS_CATEGORIES 
         add constraint FK_CONTENTS_CATEGORIES 
         foreign key (CONTENT_ID) 
         references TB_CONTENT;
     alter table TB_CONTENTS_CATEGORIES 
         add constraint FK_CATEGORIES_CONTENTS 
         foreign key (CATEGORY_ID) 
         references TB_CATEGORIES;
     alter table TB_CONTENT_PERMISSION 
         add constraint FK_CONTENT_PERMISSION 
         foreign key (CONTENT_ID) 
         references TB_CONTENT;
     alter table TB_DIARY 
         add constraint FK_DIARY_CONTENT 
         foreign key (CONTENT_ID) 
         references TB_CONTENT;
     alter table TB_DOCUMENTS 
         add constraint FK_DOCUMENT_CONTENT 
         foreign key (CONTENT_ID) 
         references TB_CONTENT;
     alter table TB_DOCUMENT_VERSIONS 
         add constraint FK_VERSION_DOC 
         foreign key (DOCUMENT_ID) 
         references TB_DOCUMENTS;
     alter table TB_DOCUMENT_VERSIONS 
         add constraint FK_DOC_ATTACHMENT 
         foreign key (ATTACHED_TO) 
         references TB_DOCUMENT_VERSIONS;
     alter table TB_DOCUMENT_VERSIONS 
         add constraint FK_DOC_VERSION_CONTENT 
         foreign key (CONTENT_ID) 
         references TB_CONTENT;
     alter table TB_DOCUMENT_VERSIONS 
         add constraint FK_VERSION_MIME_TYPE 
         foreign key (MIME_TYPE) 
         references TB_MIME_TYPES;
     alter table TB_DOMAINCLUSTERS 
         add constraint FK_DOMAINCLUSTER_DOMAIN 
         foreign key (FKAPPLICATION, FKDOMAIN) 
         references TB_DOMAINS;
     alter table TB_DOMAINNORMS 
         add constraint FK_DOMAINNORM_DOMAIN 
         foreign key (FKAPPLICATION, FKDOMAIN) 
         references TB_DOMAINS;
     alter table TB_DOMAINS 
         add constraint FK_DOMAIN_APPLICATION 
         foreign key (FKAPPLICATION) 
         references TB_APPLICATIONS;
     alter table TB_DOMAINVALUES 
         add constraint FK_VALUE_NORM 
         foreign key (FKNRMAPPLICATION, FKNRMDOMAIN, FKNORM) 
         references TB_DOMAINNORMS;
     alter table TB_DOMAINVALUES 
         add constraint FK_VALUE_CLUSTER 
         foreign key (FKCLTAPPLICATION, FKCLTDOMAIN, FKCLUSTER) 
         references TB_DOMAINCLUSTERS;
     create index IDX_STATE_FKAPPLICATION on TB_FLOWS (FKAPPLICATION);
     alter table TB_FLOWS 
         add constraint FK_FLOW_APPLICATION 
         foreign key (FKAPPLICATION) 
         references TB_APPLICATIONS;
     alter table TB_FUNCTIONS 
         add constraint FK_FUNCTION_MODULE 
         foreign key (MODULE_ID) 
         references TB_MODULES;
     alter table TB_FUNCTION_GROUPS 
         add constraint FK_FUNCTION_GROUP 
         foreign key (GROUP_ID) 
         references TB_GROUPS;
     alter table TB_FUNCTION_GROUPS 
         add constraint FK_GROUP_FUNCTION 
         foreign key (FUNCTION_ID, MODULE_ID) 
         references TB_FUNCTIONS;
     alter table TB_GROUPS 
         add constraint FK_GROUP_TYPE 
         foreign key (GROUP_TYPE) 
         references TB_GROUP_TYPES;
     alter table TB_HTML 
         add constraint FK_HTML_CONTENT 
         foreign key (CONTENT_ID) 
         references TB_CONTENT;
     create index IDX_LOGFIELDUPDATE_FKLOG on TB_LOGFIELDUPDATES (FKLOG);
     alter table TB_LOGFIELDUPDATES 
         add constraint FK_LOGFIELDUPDATE_LOG 
         foreign key (FKLOG) 
         references TB_LOGS;
     create index IDX_LOG_CRTDATE on TB_LOGS (CRTDATE);
     create index IDX_LOG_LOGCLASS on TB_LOGS (LOGCLASS);
     create index IDX_LOG_LOGLEVEL on TB_LOGS (LOGLEVEL);
     alter table TB_MENU_ITEMS 
         add constraint FK_MENUITEMS_BOXLET 
         foreign key (CONTAINER_NAME, CONTAINER_TYPE) 
         references TB_CONTAINER;
     alter table TB_MENU_ITEMS 
         add constraint FK_MENUITEMS_CATEGORY 
         foreign key (CATEGORY_ID) 
         references TB_CATEGORIES;
     alter table TB_MENU_ITEMS 
         add constraint FKCD87B7318D188391 
         foreign key (PARENT) 
         references TB_MENU_ITEMS;
     alter table TB_MENU_ITEMS 
         add constraint FKCD87B7317E940B5E 
         foreign key (PARENT) 
         references TB_MENU_ITEMS;
     alter table TB_MENU_ITEM_GROUP 
         add constraint FK41D94542CA0DE70E 
         foreign key (GROUP_ID) 
         references TB_GROUPS;
     alter table TB_MENU_ITEM_GROUP 
         add constraint FK41D94542844EA99B 
         foreign key (MENU_ITEM_ID) 
         references TB_MENU_ITEMS;
     alter table TB_MESSAGE 
         add constraint FK_USER_MSG_CONTENT 
         foreign key (CONTENT_ID) 
         references TB_CONTENT;
     alter table TB_MODULES 
         add constraint FK_MODULE_TYPE 
         foreign key (MODULE_TYPE) 
         references TB_MODULE_TYPES;
     alter table TB_NEWS 
         add constraint FK_NEWS_CONTENT 
         foreign key (CONTENT_ID) 
         references TB_CONTENT;
     alter table TB_NEWS_LINK 
         add constraint FK_NEWS_LINK 
         foreign key (NEWS_ID) 
         references TB_NEWS;
     alter table TB_NEWS_PICTURE 
         add constraint FK_NEWS_PICTURE 
         foreign key (NEWS_ID) 
         references TB_NEWS;
     alter table TB_OLD_PASSWORDS 
         add constraint FK_USER_OLD_PSWD 
         foreign key (USER_ID) 
         references TB_USER_PROFILES;
     alter table TB_PAGES 
         add constraint FK_SESSION_PAGES 
         foreign key (TRACKING_ID) 
         references TB_TRACKING;
     alter table TB_POLL 
         add constraint FK_POLL_CONTENT 
         foreign key (CONTENT_ID) 
         references TB_CONTENT;
     alter table TB_POLL_USER 
         add constraint FK_POLL_USER 
         foreign key (POLL_ID) 
         references TB_POLL;
     alter table TB_POPUP_SCHEDULE 
         add constraint FK_POPUP_SCHEDULE 
         foreign key (SCHEDULE_ID) 
         references TB_CONTAINER_SCHEDULE;
     alter table TB_SECURITY_CONSTRAINT 
         add constraint FK_GROUP_RES_COLL 
         foreign key (COLLECTION_NAME) 
         references TB_RESOURCE_COLLECTION;
     alter table TB_SECURITY_CONSTRAINT 
         add constraint FK_RES_COLL_GROUP 
         foreign key (GROUP_ID) 
         references TB_GROUPS;
     create index IDX_CUSTODIAN_DELETED on TB_SP_CUSTODIAN_ACCOUNTS (ISDELETED);
     alter table TB_SP_CUSTODIAN_ACCOUNTS 
         add constraint FK_PROVIDER 
         foreign key (FKPROVIDER) 
         references TB_CA_EVENTPROVIDERS;
     create index IDX_CUSTOMER_DELETED on TB_SP_CUSTOMERS (ISDELETED);
     create index IDX_CUSTOMER_FKMANAGERGROUP on TB_SP_CUSTOMERS (FKCUSTMANAGERGROUP);
     alter table TB_SP_CUSTOMERS 
         add constraint FK_CUSTOMER_MANAGER 
         foreign key (FKCUSTMANAGER) 
         references TB_SP_MANAGERS;
     alter table TB_SP_CUSTOMERS 
         add constraint FK_CUSTOMER_MANAGERGROUP 
         foreign key (FKCUSTMANAGERGROUP) 
         references TB_SP_MANAGERGROUPS;
     create index IDX_MANAGERGROUP_DELETED on TB_SP_MANAGERGROUPS (ISDELETED);
     create index IDX_MANAGER_FKMANAGERGROUP on TB_SP_MANAGERS (FKMANAGERGROUP);
     create index IDX_MANAGER_DELETED on TB_SP_MANAGERS (ISDELETED);
     alter table TB_SP_MANAGERS 
         add constraint FK_MANAGER_MANAGERGROUP 
         foreign key (FKMANAGERGROUP) 
         references TB_SP_MANAGERGROUPS;
     create index IDX_MARKET_MIC on TB_SP_MARKETS (MIC);
     create index IDX_MARKET_COUNTRY on TB_SP_MARKETS (COUNTRY);
     create index IDX_MARKET_DELETED on TB_SP_MARKETS (ISDELETED);
     create index IDX_PROCESS on TB_SP_PLANNING_PROCESS (FKPROCESS);
     alter table TB_SP_PLANNING_PROCESS 
         add constraint FK_PROCESS 
         foreign key (FKPROCESS) 
         references TB_SP_PROCESS;
     create index IDX_SECURITY_FKMARKET on TB_SP_SECURITIES (FKMARKET);
     create index IDX_SECURITY_DELETED on TB_SP_SECURITIES (ISDELETED);
     create index IDX_SECURITY_TICKER on TB_SP_SECURITIES (TICKER);
     create index IDX_SECURITY_ISIN on TB_SP_SECURITIES (ISIN);
     create index IDX_SECURITY_FINANCIALASSET on TB_SP_SECURITIES (FKFINANCIALASSET);
     create index IDX_SECURITY_DETAIL on TB_SP_SECURITIES (FKDETAIL);
     create index IDX_SECURITY_CUSIP on TB_SP_SECURITIES (CUSIP);
     create index IDX_SECURITY_SEDOL on TB_SP_SECURITIES (SEDOL);
     create index IDX_SECURITY_SECURITYCLASS on TB_SP_SECURITIES (SECURITYCLASS);
     create index IDX_SECURITY_INFPROV2 on TB_SP_SECURITIES (PROVIDER_ID2);
     create index IDX_SECURITY_INFPROV1 on TB_SP_SECURITIES (PROVIDER_ID1);
     create index IDX_SECURITY_PLANIFICATION on TB_SP_SECURITIES (FKPLANIFICATION);
     create index IDX_SECURITY_INFPROV3 on TB_SP_SECURITIES (PROVIDER_ID3);
     alter table TB_SP_SECURITIES 
         add constraint FK_FINACIAL_ASSET 
         foreign key (FKFINANCIALASSET) 
         references TB_SP_SECURITY_FASSETS;
     alter table TB_SP_SECURITIES 
         add constraint FK_SECURITY_DETAIL 
         foreign key (FKDETAIL) 
         references TB_SP_SECURITYDETAILS;
     alter table TB_SP_SECURITIES 
         add constraint FK_SECURITY_MARKET 
         foreign key (FKMARKET) 
         references TB_SP_MARKETS;
     alter table TB_SP_SECURITIES 
         add constraint FK_PLANIFICATION 
         foreign key (FKPLANIFICATION) 
         references TB_SP_PLANNING_PROCESS;
     create index IDX_SECDETAIL_DELETED on TB_SP_SECURITYDETAILS (ISDELETED);
     create index IDX_SECDETAIL_VERSION on TB_SP_SECURITYDETAILS (VERSION);
     create index IDX_SECPORT_FKSECURITY on TB_SP_SECURITYPORTFOLIO (FKSECURITY);
     create index IDX_SECPORT_FKPORTFOLIO on TB_SP_SECURITYPORTFOLIO (FKCUSTOMER);
     create index IDX_SECPORT_DELETED on TB_SP_SECURITYPORTFOLIO (ISDELETED);
     create index IDX_SECPORT_EMISSIONDATE on TB_SP_SECURITYPORTFOLIO (EMISSIONDATE);
     create index IDX_SECPORT_VERSION on TB_SP_SECURITYPORTFOLIO (VERSION);
     alter table TB_SP_SECURITYPORTFOLIO 
         add constraint FK_SECPORT_SECURITY 
         foreign key (FKSECURITY) 
         references TB_SP_SECURITIES;
     alter table TB_SP_SECURITYPORTFOLIO 
         add constraint FK_SECPORT_PROVACCOUNTS 
         foreign key (FKPROVIDERACCOUNT) 
         references TB_SP_CUSTODIAN_ACCOUNTS;
     alter table TB_SP_SECURITYPORTFOLIO 
         add constraint FK_SECPORT_CUSTOMER 
         foreign key (FKCUSTOMER) 
         references TB_SP_CUSTOMERS;
     create index IDX_SECURITYFA_NAME on TB_SP_SECURITY_FASSETS (NAME);
     create index IDX_SECURITYFADET_FA on TB_SP_SECURITY_FASSETS_DETAILS (FKSECURITYFA);
     create index IDX_SECURITYFADET_NAME on TB_SP_SECURITY_FASSETS_DETAILS (NAME);
     alter table TB_SP_SECURITY_FASSETS_DETAILS 
         add constraint FK_DETAIL_SECFA 
         foreign key (FKSECURITYFA) 
         references TB_SP_SECURITY_FASSETS;
     alter table TB_SP_SECURITY_PLANNING 
         add constraint FK_SECURITY_PLANNING 
         foreign key (FKSECURITY) 
         references TB_SP_SECURITIES;
     alter table TB_SP_SECURITY_PLANNING 
         add constraint FK_PLANNING 
         foreign key (FKPLANNING) 
         references TB_SP_PLANNING_PROCESS;
     create index IDX_STATE_CODE on TB_STATES (CODE);
     create index IDX_STATE_FKFLOW on TB_STATES (FKFLOW);
     alter table TB_STATES 
         add constraint FK_STATE_FLOW 
         foreign key (FKFLOW) 
         references TB_FLOWS;
     create index IDX_TRANSITION_CODE on TB_TRANSITIONS (CODE);
     create index IDX_TRANSITION_FKFLOW on TB_TRANSITIONS (FKFLOW);
     alter table TB_TRANSITIONS 
         add constraint FK_TRANSITION_ENDSTATE 
         foreign key (FKENDSTATEFLOW, FKENDSTATE) 
         references TB_STATES;
     alter table TB_TRANSITIONS 
         add constraint FK_TRANSITION_FLOW 
         foreign key (FKFLOW) 
         references TB_FLOWS;
     alter table TB_TRANSITIONS 
         add constraint FK_TRANSITION_INITSTATE 
         foreign key (FKINITIALSTATEFLOW, FKINITIALSTATE) 
         references TB_STATES;
     alter table TB_USER_GROUP 
         add constraint FK_USER_GROUP 
         foreign key (USER_ID) 
         references TB_USER_PROFILES;
     alter table TB_USER_PROFILES 
         add constraint FK_USER_TYPE 
         foreign key (USER_TYPE) 
         references TB_USER_TYPE;
