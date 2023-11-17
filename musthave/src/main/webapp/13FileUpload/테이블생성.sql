CREATE TABLE myfile (
                        idx int PRIMARY KEY AUTO_INCREMENT,
                        title	varchar(200) NOT NULL,
                        cate	varchar(30),
                        ofile	varchar(100) NOT NULL,
                        sfile	varchar(30) NOT NULL,
                        postdate date	DEFAULT current_date NOT null
);


USE model1;