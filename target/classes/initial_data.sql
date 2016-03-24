INSERT INTO users(user_firstname, user_lastname, user_email, user_password) VALUES ('Jerry', 'Murdock', 'random@gmail.com', 'Password1')
INSERT INTO users(user_firstname, user_lastname, user_email, user_password) VALUES ('Jerome', 'Gnome', 'dumb@gmail.com', 'Password2')

INSERT INTO participant(name) VALUES ('Karen')
INSERT INTO participant(name) VALUES ('Donald')

INSERT INTO task(due_date, details, title, location, latitude, longitude, timezone, last_modified_date, sync_date, user_id, completed) VALUES ('1458239800000', 'details, details, details...', 'First test title', 'Inner Mongolia', 0, 0, 'America/Phoenix', 0, 0, 1, false);
INSERT INTO task(due_date, details, title, location, latitude, longitude, timezone, last_modified_date, sync_date, user_id, completed) VALUES ('1478333300000', 'details are scarce', 'New test title', 'the alley', 0, 0, 'America/Phoenix', 0, 0, 1, true);
INSERT INTO task(due_date, details, title, location, latitude, longitude, timezone, last_modified_date, sync_date, user_id, completed) VALUES ('1457332200000', 'the devil is in the details', 'Another test title', '3 Exeter Drive', 0, 0, 'America/Phoenix', 0, 0, 2, false);

INSERT INTO task_participant(task_id, participants_id) VALUES (1, 1)
INSERT INTO task_participant(task_id, participants_id) VALUES (2, 1)
INSERT INTO task_participant(task_id, participants_id) VALUES (2, 2)
INSERT INTO task_participant(task_id, participants_id) VALUES (3, 2)


