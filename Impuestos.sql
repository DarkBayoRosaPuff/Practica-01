/*
Tabla creada para almacenar los datos de las ventas registradas
Contienen un id como llave primaria de la venta,
una fecha de captura,
una columna para la cantidad bruta de la venta,
el nombre del capturista,
y el IVA
*/
CREATE TABLE ventas(
       fecha_de_captura DATE PRIMARY KEY,
       capturista VARCHAR(50) NOT NULL,
       venta_bruta REAL NOT NULL,
       IVA REAL NOT NULL,
       venta_neta REAL NOT NULL
       );