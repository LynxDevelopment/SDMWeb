INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'CREATE', 'USER', '/xweb-res/users/newuser.xwb', 'Nuovo Utente', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'DELETE', 'USER', 'deleteUser.do', 'Elimina Utente', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'UPDATE', 'USER', '/xweb-res/users/edituser.xwb', 'Modifica Utente', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'CLONE', 'USER', '/xweb-res/users/cloneuser.xwb', 'Copia utente', 1 );
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'CREATE', 'GROUPS', '/xweb-res/groups/newgroup.xwb', 'Nuovo Gruppo', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'UPDATE', 'GROUPS', 'updateGroups.do', 'Modifica Gruppo', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'DELETE', 'GROUPS', 'deleteGroup.do', 'Elimina Gruppo', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'CLONE', 'GROUPS', '/xweb-res/users/clonegroup.xwb', 'Copia gruppo', 1 );
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'ADD_USER', 'GROUPS', 'addUserToGroup.do', 'Aggiungi Utente al Gruppo', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'ADD_FUNCTION', 'GROUPS', 'addFunctionToGroup.do', 'Aggiungi Funzioni', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'REMOVE_FUNCTION', 'GROUPS', 'removeFunctionFromGroup.do', 'Rimuovi Funzioni', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'REMOVE_GROUP', 'GROUPS', 'removeUserFromGroup.do', 'Rimuovi Utente dai Gruppi', NULL);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'FILTER', 'GROUPS', 'grouptypeslist.xwb', 'Trova', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'CREATE', 'MENU', '/xweb-res/menus/newmenu.xwb', 'Nuovo Menu', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'UPDATE', 'MENU', '/xweb-res/menus/editmenu.xwb', 'Modifica Menu', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'DELETE', 'MENU', 'deleteMenu.do', 'Elimina Menu', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'CLONE_MENU', 'MENU', 'cloneMenu.do', 'Copia Menu', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'CLONE_MENU_ITEM', 'MENU', 'cloneMenuItem.do', 'Copia Menu Item', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'CREATE', 'MODULES', '/xweb-res/modules/newmodule.xwb', 'Nuovo Modulo', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'ADD_GROUP', 'USER', 'addUserToGroup.do', 'Associa Utente ai Gruppi', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'REMOVE_GROUP', 'USER', 'removeUserFromGroup.do', 'Rimuovi Utente dai Gruppi', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'DELETE', 'MODULES', 'deleteModules.do', 'Elimina Modulo', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'UPDATE', 'MODULES', '/xweb-res/modules/editmodule.xwb', 'Modifica Modulo', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'ADD_FUNCTION', 'MODULES', 'addFunctionToModules.do', 'Aggiungi Funzione', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'REMOVE_FUNCTION', 'MODULES', 'deleteFunctions.do', 'Elimina Funzione', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'CHANGE_PWD', 'USER', NULL, 'Cambia Password', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'FILTER', 'USER', 'filterUser.do', 'Trova', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'CREATE_MENUITEM', 'MENU', '/xweb-res/menus/newmenuitem.xwb', 'Nuovo MenuItem', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'UPDATE_MENUITEM', 'MENU', '/xweb-res/menus/editmenuitem.xwb', 'Modifica MenuItem', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'DELETE_MENUITEM', 'MENU', 'deleteMenuItem.do', 'Elimina MenuItem', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'FILTER', 'MODULES', 'filterModules.do', 'Trova', 1);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'VIEW_STATISTICS', 'STATISTICS', '/xweb-res/statistics/index.xwb', 'Visualizza Statistiche', NULL);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'ADD_GROUP', 'MENU', 'addMenuItemToGroup.do', 'Associa gruppi', NULL);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'REMOVE_GROUP', 'MENU', 'removeMenuItemFromGroup.do', 'Rimuovi gruppi', NULL);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'DELETE', 'CACHE', 'deleteCache.do', 'Cancella cache', NULL);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'DELETE_CACHED_OBJECT', 'CACHE', 'deleteCachedObject.do', 'Cancella oggetto', NULL);
INSERT INTO TB_FUNCTIONS ( FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO ) VALUES ( 'UPDATE', 'CACHE', 'updateCache.do', 'Salva', NULL);
INSERT INTO TB_FUNCTIONS (FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO) VALUES ('DUMP_INFO', 'TRACKING', '/xweb-res/tracking/dump_infos.do', 'Salva', NULL);
INSERT INTO TB_FUNCTIONS (FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO) VALUES ('report', 'TRACKING', '/xweb-res/tracking/tracking_report.xwb', 'Report', NULL);
INSERT INTO TB_FUNCTIONS (FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO) VALUES ('PROFILER_REPORT', 'TUNING', '/xweb-res/develop/profiler_report.xwb', 'Profiler Report', NULL);
INSERT INTO TB_FUNCTIONS (FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO) VALUES ('RESTART', 'TUNING', '#', 'Restart XWeb', NULL);
INSERT INTO TB_FUNCTIONS (FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO) VALUES ('MONITOR', 'TUNING', '#', 'Developer Tools', NULL);
INSERT INTO TB_FUNCTIONS (FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO) VALUES ('VISUALIZZA', 'AUTH', NULL, 'Visualizza', NULL);
INSERT INTO TB_FUNCTIONS (FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO) VALUES ('CREATE', 'AUTH', NULL, 'Crea', NULL);
INSERT INTO TB_FUNCTIONS (FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO) VALUES ('DELETE', 'AUTH', NULL, 'Elimina', NULL);
INSERT INTO TB_FUNCTIONS (FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO) VALUES ('UPDATE', 'AUTH', NULL, 'Modifica', NULL);
INSERT INTO TB_FUNCTIONS (FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO) VALUES ('BROWSE', 'FILEMANAGER', NULL, 'Naviga', NULL);
INSERT INTO TB_FUNCTIONS (FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO) VALUES ('SHOW', 'ICONS', NULL, 'Icons', NULL);
INSERT INTO TB_FUNCTIONS (FUNCTION_ID, MODULE_ID, FUNCTION_URI, FUNCTION_TITLE, WF_MGR_AUTO) VALUES ('DEPLOY', 'DEPLOYER', NULL, 'Deploy', NULL);