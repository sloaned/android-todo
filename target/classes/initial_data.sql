INSERT INTO users(user_firstname, user_lastname, user_email, user_password) VALUES ('Jerry', 'Murdock', 'random@gmail.com', 'Password1')
INSERT INTO users(user_firstname, user_lastname, user_email, user_password) VALUES ('Jerome', 'Gnome', 'dumb@gmail.com', 'Password2')

<<<<<<< HEAD
INSERT INTO task(due_date, details, title, user_id) VALUES ('Janurary', 'details, details, details...', 'First test title', 1);
INSERT INTO task(due_date, details, title, user_id) VALUES ('Feburary', 'details are scarce', 'New test title', 1);
INSERT INTO task(due_date, details, title, user_id) VALUES ('March', 'the devil is in the details', 'Another test title', 2);
=======
INSERT INTO task(due_date, details, title) VALUES ('Janurary', 'details, details, details...', 'First test title');
INSERT INTO task(due_date, details, title) VALUES ('Feburary', 'details are scarce', 'New test title');
INSERT INTO task(due_date, details, title) VALUES ('March', 'the devil is in the details', 'Another test title');

INSERT INTO task_users(task_id, user_id) VALUES (1, 1);
INSERT INTO task_users(task_id, user_id) VALUES (1, 2);
INSERT INTO task_users(task_id, user_id) VALUES (2, 2);
>>>>>>> b0e377aebcac85e27d68ac191a5f473e5a09bc79
