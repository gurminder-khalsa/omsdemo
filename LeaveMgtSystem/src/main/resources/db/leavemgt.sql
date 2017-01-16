--
-- TOC entry 2165 (class 0 OID 16553)
-- Dependencies: 188
-- Data for Name: lms_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO lms_user (user_id, first_name, last_name, password, username, manager_user_id) VALUES (1, 'Gurminder', 'Singh', 'Passw0rd', 'gurminder.singh', NULL);
INSERT INTO lms_user (user_id, first_name, last_name, password, username, manager_user_id) VALUES (2, 'Manoj', 'Sharma', 'Passw0rd', 'manoj.sharma', 1);
INSERT INTO lms_user (user_id, first_name, last_name, password, username, manager_user_id) VALUES (3, 'Ankit', 'Mehta', 'Passw0rd', 'ankit.mehta', 1);
INSERT INTO lms_user (user_id, first_name, last_name, password, username, manager_user_id) VALUES (4, 'Mohit', 'Garg', 'Passw0rd', 'mohit.garg', 1);
INSERT INTO lms_user (user_id, first_name, last_name, password, username, manager_user_id) VALUES (5, 'Rajni', 'Ubhi', 'Passw0rd', 'rajni.ubhi', 1);
INSERT INTO lms_user (user_id, first_name, last_name, password, username, manager_user_id) VALUES (6, 'Tarminder', 'Kumar', 'Passw0rd', 'tarminder.kumar', 1);


--
-- TOC entry 2166 (class 0 OID 16573)
-- Dependencies: 189
-- Data for Name: lms_user_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO lms_user_role (user_role_id, role, user_user_id) VALUES (1, 'ROLE_ADMIN', 1);
INSERT INTO lms_user_role (user_role_id, role, user_user_id) VALUES (4, 'ROLE_USER', 3);
INSERT INTO lms_user_role (user_role_id, role, user_user_id) VALUES (5, 'ROLE_USER', 4);
INSERT INTO lms_user_role (user_role_id, role, user_user_id) VALUES (6, 'ROLE_USER', 5);
INSERT INTO lms_user_role (user_role_id, role, user_user_id) VALUES (7, 'ROLE_USER', 6);
INSERT INTO lms_user_role (user_role_id, role, user_user_id) VALUES (2, 'ROLE_MANAGER', 1);
INSERT INTO lms_user_role (user_role_id, role, user_user_id) VALUES (3, 'ROLE_USER', 2);

--
-- TOC entry 2164 (class 0 OID 16545)
-- Dependencies: 187
-- Data for Name: lms_leave_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO lms_leave_type (leave_type_id, leave_description, leave_type) VALUES (1, 'Privilege Leave', 'PL');
INSERT INTO lms_leave_type (leave_type_id, leave_description, leave_type) VALUES (2, 'Sick Leave', 'SL');
INSERT INTO lms_leave_type (leave_type_id, leave_description, leave_type) VALUES (3, 'Casual Leave', 'CL');
INSERT INTO lms_leave_type (leave_type_id, leave_description, leave_type) VALUES (4, 'Compensatory Off', 'CO');
INSERT INTO lms_leave_type (leave_type_id, leave_description, leave_type) VALUES (5, 'Loss Of Pay', 'LOP');

--
-- TOC entry 2168 (class 0 OID 16601)
-- Dependencies: 191
-- Data for Name: user_leaves; Type: TABLE DATA; Schema: public; Owner: postgres
--

-- Inserting leaves for User: Gurminder
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 1, (select user_id from lms_user where first_name='Gurminder'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 2, (select user_id from lms_user where first_name='Gurminder'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 3, (select user_id from lms_user where first_name='Gurminder'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 4, (select user_id from lms_user where first_name='Gurminder'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), -1, 5, (select user_id from lms_user where first_name='Gurminder'));

-- Inserting leaves for User: Manoj
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 1, (select user_id from lms_user where first_name='Manoj'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 2, (select user_id from lms_user where first_name='Manoj'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 3, (select user_id from lms_user where first_name='Manoj'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 4, (select user_id from lms_user where first_name='Manoj'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), -1, 5, (select user_id from lms_user where first_name='Manoj'));

-- Inserting leaves for User: Ankit
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 1, (select user_id from lms_user where first_name='Ankit'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 2, (select user_id from lms_user where first_name='Ankit'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 3, (select user_id from lms_user where first_name='Ankit'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 4, (select user_id from lms_user where first_name='Ankit'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), -1, 5, (select user_id from lms_user where first_name='Ankit'));

-- Inserting leaves for User: Mohit
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 1, (select user_id from lms_user where first_name='Mohit'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 2, (select user_id from lms_user where first_name='Mohit'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 3, (select user_id from lms_user where first_name='Mohit'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 4, (select user_id from lms_user where first_name='Mohit'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), -1, 5, (select user_id from lms_user where first_name='Mohit'));

-- Inserting leaves for User: Rajni
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 1, (select user_id from lms_user where first_name='Rajni'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 2, (select user_id from lms_user where first_name='Rajni'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 3, (select user_id from lms_user where first_name='Rajni'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 4, (select user_id from lms_user where first_name='Rajni'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), -1, 5, (select user_id from lms_user where first_name='Rajni'));

-- Inserting leaves for User: Tarminder
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 1, (select user_id from lms_user where first_name='Tarminder'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 2, (select user_id from lms_user where first_name='Tarminder'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 3, (select user_id from lms_user where first_name='Tarminder'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), 5, 4, (select user_id from lms_user where first_name='Tarminder'));
INSERT INTO user_leaves (user_leave_id, number_of_leaves, leavetype_leave_type_id, user_user_id) VALUES ((select count(user_leave_id)+1 from user_leaves), -1, 5, (select user_id from lms_user where first_name='Tarminder'));

INSERT INTO public.lms_user_contact(user_contact_id, email_address, mobile_number, phone_number, user_user_id) VALUES ((select count(user_contact_id)+1 from lms_user_contact), 'gurminder.singh@trantorinc.com', '1234567890', '1234567890', (select user_id from lms_user where first_name='Gurminder'));
    
INSERT INTO public.lms_user_contact(user_contact_id, email_address, mobile_number, phone_number, user_user_id) VALUES ((select count(user_contact_id)+1 from lms_user_contact), 'manoj4.kumar@trantorinc.com', '1234567890', '1234567890', (select user_id from lms_user where first_name='Manoj'));

INSERT INTO public.lms_user_contact(user_contact_id, email_address, mobile_number, phone_number, user_user_id) VALUES ((select count(user_contact_id)+1 from lms_user_contact), 'ankit.mehta@trantorinc.com', '1234567890', '1234567890', (select user_id from lms_user where first_name='Ankit'));
    
INSERT INTO public.lms_user_contact(user_contact_id, email_address, mobile_number, phone_number, user_user_id) VALUES ((select count(user_contact_id)+1 from lms_user_contact), 'mohit.garg@trantorinc.com', '1234567890', '1234567890', (select user_id from lms_user where first_name='Mohit'));
    
INSERT INTO public.lms_user_contact(user_contact_id, email_address, mobile_number, phone_number, user_user_id) VALUES ((select count(user_contact_id)+1 from lms_user_contact), 'rajni.ubhi@trantorinc.com', '1234567890', '1234567890', (select user_id from lms_user where first_name='Rajni'));

INSERT INTO public.lms_user_contact(user_contact_id, email_address, mobile_number, phone_number, user_user_id) VALUES ((select count(user_contact_id)+1 from lms_user_contact), 'tarminder.kumar@trantorinc.com', '1234567890', '1234567890', (select user_id from lms_user where first_name='Tarminder'));
    
    