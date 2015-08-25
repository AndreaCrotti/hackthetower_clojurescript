-- name: get-password
-- Get user password
SELECT password FROM users
WHERE username=:username;

-- name: user-exists
-- check user existence
SELECT EXISTS (SELECT 1 FROM users WHERE username = :username);


-- name: create-users-schema
-- Create the users schema
CREATE TABLE users (
password VARCHAR(12),
username VARCHAR(12),
);
