drop function if exists get_users_stats_average_weight(boolean);

create function get_users_stats_average_weight(is_deleted_param boolean) returns double precision
    language sql
as
$$
select avg(weight)
from carshop.users
where is_deleted = is_deleted_param;
$$;

alter function get_users_stats_average_weight(boolean) owner to postgres;