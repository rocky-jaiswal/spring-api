CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE "user_preferences" (
  "id" uuid NOT NULL DEFAULT gen_random_uuid(),
  "is_member" boolean NOT NULL DEFAULT '0',
  "user_id" uuid UNIQUE NOT NULL,

  "created_at" timestamptz DEFAULT CURRENT_TIMESTAMP,
  "updated_at" timestamptz DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT "user_preferences_pkey" PRIMARY KEY ("id")
);

ALTER TABLE "user_preferences" ADD CONSTRAINT "user_preferences_user_id_foreign" FOREIGN KEY ("user_id") REFERENCES "users" ("id");
CREATE INDEX "idx_user_preferences_user_id" ON "user_preferences" ("user_id");

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

   insert into user_preferences(is_member, user_id) values(TRUE, user_id_1);
   insert into user_preferences(is_member, user_id) values(FALSE, user_id_2);
end block_1 $$;
