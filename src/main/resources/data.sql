/* 従業員テーブルのデータ */
INSERT INTO employee(employee_id, employee_name, age)
VALUES(1, '山田太郎', 30);

/* ユーザーマスタのデータ（アドミン権限） */
INSERT INTO m_user(user_id, password, user_name, birthday, age, marriage, type_of_engineer, using_language, role)
VALUES('admin@test.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '権限たかし', '2000-01-23', 20, false, 1, 2, 'ROLE_ADMIN');

/* ユーザーマスタのデータ1（一般権限） */
INSERT INTO m_user(user_id, password, user_name, birthday, age, marriage, type_of_engineer, using_language, role)
VALUES('test1@test.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '試験たかし', '2010-02-22', 10, false, 2, 3, 'ROLE_GENERAL');

/* ユーザーマスタのデータ2（一般権限） */
INSERT INTO m_user(user_id, password, user_name, birthday, age, marriage, type_of_engineer, using_language, role)
VALUES('test2@test.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '試験はなこ', '2010-02-22', 10, false, 0, 0, 'ROLE_GENERAL');

/* ユーザーマスタのデータ3（一般権限） */
INSERT INTO m_user(user_id, password, user_name, birthday, age, marriage, type_of_engineer, using_language, role)
VALUES('test3@test.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '試験やすし', '2010-02-22', 10, false, 2, 2, 'ROLE_GENERAL');

/* ユーザーマスタのデータ4（一般権限） */
INSERT INTO m_user(user_id, password, user_name, birthday, age, marriage, type_of_engineer, using_language, role)
VALUES('test4@test.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '試験太郎', '2010-02-22', 10, false, 2, 4, 'ROLE_GENERAL');

/* ユーザーマスタのデータ5（一般権限） */
INSERT INTO m_user(user_id, password, user_name, birthday, age, marriage, type_of_engineer, using_language, role)
VALUES('test5@test.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '試験二郎', '2010-02-22', 10, false, 1, 3, 'ROLE_GENERAL');
