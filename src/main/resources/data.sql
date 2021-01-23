/* 従業員テーブルのデータ */
INSERT INTO employee(employee_id, employee_name, age)
VALUES(1, '山田太郎', 30);

/* ユーザーマスタのデータ（アドミン権限） */
INSERT INTO m_user(user_id, password, user_name, birthday, age, marriage, role)
VALUES('admin@test.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '権限たかし', '2000-01-23', 20, false, 'ROLE_ADMIN');

/* ユーザーマスタのデータ（一般権限） */
INSERT INTO m_user(user_id, password, user_name, birthday, age, marriage, role)
VALUES('test@test.co.jp', '$2a$10$xRTXvpMWly0oGiu65WZlm.3YL95LGVV2ASFjDhe6WF4.Qji1huIPa', '試験たかし', '2010-02-22', 10, false, 'ROLE_GENERAL');
