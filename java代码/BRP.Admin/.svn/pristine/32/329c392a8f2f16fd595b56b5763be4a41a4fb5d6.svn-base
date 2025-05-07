
insert into sys_operation_target(code, name)
select table_name,table_name
FROM information_schema.tables
WHERE table_schema = 'brp'
and table_name not in (select code from sys_operation_target)