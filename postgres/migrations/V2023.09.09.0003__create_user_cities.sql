CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE "user_cities" (
  "id" uuid NOT NULL DEFAULT gen_random_uuid(),
  "city_name" text,
  "user_id" uuid NOT NULL,

  "created_at" timestamptz DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamptz DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT "user_cities_pkey" PRIMARY KEY ("id")
);

ALTER TABLE "user_cities" ADD CONSTRAINT "user_cities_user_id_foreign" FOREIGN KEY ("user_id") REFERENCES "users" ("id");
CREATE INDEX "idx_user_cities_user_id" ON "user_cities" ("user_id");

do $$
<<block_1>>
declare
  user_id_1 users.id%TYPE;
  user_id_2 users.id%TYPE;
begin
   select id
   into user_id_1
   from users
   where email = 'ryanj@example.com';

   select id
   into user_id_2
   from users
   where email = 'carysj@example.com';

   insert into user_cities(city_name, user_id) values('Berlin', user_id_1);
   insert into user_cities(city_name, user_id) values('Hamburg', user_id_1);
   insert into user_cities(city_name, user_id) values('Bremen', user_id_1);

   insert into user_cities(city_name, user_id) values('Oslo', user_id_2);
   insert into user_cities(city_name, user_id) values('Helsinki', user_id_2);
end block_1 $$;
