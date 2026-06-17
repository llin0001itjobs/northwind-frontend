-- Use uppercase for ROLE if it's uppercase in schema.sql
INSERT INTO ROLE (ID, "TYPE", DESCRIPTION) VALUES (1, 'ROLE_ADMIN', 'Administrator');
INSERT INTO ROLE (ID, "TYPE", DESCRIPTION) VALUES (2, 'ROLE_USER', 'Default User');

-- Use double quotes for "USER" and explicit column names
INSERT INTO "USER" (ID, USERNAME, EMAIL, PASSWORD, ENABLED, EMAIL_VERIFIED, ROLE_ID)
VALUES (3, 'qwerty01abc', 'llin0001test01@gmail.com', '$2a$10$PHUrSp5fpiEHB1eLCLVRBui4OljHx.hqyIVYL2omelm7nXezGPkL.', true, true, 2);

INSERT INTO "USER" (ID, USERNAME, EMAIL, PASSWORD, ENABLED, EMAIL_VERIFIED, ROLE_ID)
VALUES (4, 'llin0001itjobs', 'llin0001itjobs@github.com', 'd64eb8cf-8d2d-40ea-9649-7174d41528eb', false, false, 2);
