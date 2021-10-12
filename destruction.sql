BEGIN;

DROP TABLE IF EXISTS "Auberge-Inn"."CommoditesChambres";
DROP TABLE IF EXISTS "Auberge-Inn"."Commodites";
DROP TABLE IF EXISTS "Auberge-Inn"."Reservations";
DROP TABLE IF EXISTS "Auberge-Inn"."Clients";
DROP TABLE IF EXISTS "Auberge-Inn"."Chambres";

DROP SCHEMA IF EXISTS "Auberge-Inn" CASCADE;

COMMIT;