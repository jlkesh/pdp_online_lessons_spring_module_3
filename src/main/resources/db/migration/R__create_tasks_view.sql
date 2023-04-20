drop view if exists tasks_view cascade ;
drop view if exists task_view cascade ;
create view task_view as
select id, name, label
from tasks;