
--clean up db

drop table rph_renter_profile;


create table rph_renter_profile(
                                   id serial primary key,
                                   tentative_move_in_date timestamp,
                                   user_id int references rph_user(user_id),
                                   num_tenants int,
                                   smoke_friendly boolean,
                                   credit_hist_range varchar(20),
                                   pets_allowed boolean,
                                   pets jsonb,
                                   single_room boolean,
                                   roommates_comfortable int,
                                   work_zip int,
                                   company_name varchar(20),
                                   income_range varchar(30)

)