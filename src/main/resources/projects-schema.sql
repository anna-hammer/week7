USE PROJECT;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS project_category;
DROP TABLE IF EXISTS step;
DROP TABLE IF EXISTS project;

CREATE TABLE project (
	project_id INT AUTO_INCREMENT NOT NULL,
    project_name VARCHAR(128) NOT NULL,
    estimated_hours DECIMAL(7,2),
    actual_hours DECIMAL(7,2),
    difficulty INT,
    notes TEXT,
    primary key (project_id)
);

CREATE TABLE step (
	step_id INT AUTO_INCREMENT NOT NULL,
    project_id INT NOT NULL,
    step_text TEXT,
    step_order INT,
    primary key (step_id),
    foreign key (project_id) references project (project_id)
);
CREATE TABLE project_category (
	project_id INT NOT NULL,
    category_id INT NOT NULL,
    unique key (project_id, category_id)
);

CREATE TABLE material (
	material_id INT AUTO_INCREMENT NOT NULL,
    project_id INT NOT NULL,
    material_name VARCHAR(128) NOT NULL,
    num_required INT,
    cost DECIMAL(7,2),
    primary key (material_id),
    foreign key (project_id) references project (project_id)
);
CREATE TABLE category (
	category_id INT AUTO_INCREMENT NOT NULL,
    category_name VARCHAR(128) NOT NULL,
    primary key (category_id)
);
INSERT INTO category (category_name) VALUES ('Doors and Windows');

SELECT * FROM project;

INSERT INTO material (project_id, material_name, num_required) 
VALUES
(1, '2-inch screws', 20);

INSERT INTO step (project_id, step_text, step_order)
VALUES
(1, 'Screw door hangers on the top and bottom of each side of the door frame', 1);

INSERT INTO project_category (project_id, category_id)
VALUES
(1, 2);



