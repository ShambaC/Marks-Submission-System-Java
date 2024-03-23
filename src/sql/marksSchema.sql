create table marks
(
    roll int(6) references studentTable(roll),
    paperName varchar(30),
    paperCode varchar(8),
    paperType varchar(2),
    half varchar(5),
    FullMarks int(3),
    ObtMarks int(3),
    -- Constraint to not allow duplicate marks value for a particular paper of a particular student
    constraint ck_unique_roll_paper unique (roll, paperCode, paperType),
    -- Constraint to not allow invalid marks
    constraint ck_valid_marks check (ObtMarks <= FullMarks)
);