create table marks
(
    roll int(6) references student(roll),
    paperCode varchar(8) references paper(paperCode),
    paperType varchar(2),
    half varchar(5),
    FullMarks int(3),
    ObtMarks int(3),
    constraint ck_unique_roll_paper unique (roll, paperCode, paperType),
    constraint ck_valid_marks check (ObtMarks <= FullMarks)
);