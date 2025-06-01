# Database basic
### This document presents a basic structure about models 

### Models
#### Room type
```sql
INSERT INTO room_type (id, code, name, description) VALUES
(1, 'SGL', 'Single', '1 cama individual, para una sola persona. Ideal para viajeros solos.'),
(2, 'DBL', 'Double', '1 cama doble (matrimonial), adecuada para 2 personas.'),
(3, 'TWN', 'Twin', '2 camas individuales separadas. Ideal para compañeros de viaje.'),
(4, 'TPL', 'Triple', '3 camas individuales o combinación para 3 personas.'),
(5, 'QAD', 'Quad', 'Alojamiento para 4 personas, típicamente 2 camas dobles.'),
(6, 'QEN', 'Queen', '1 cama Queen-size. Más amplia que una doble.'),
(7, 'KNG', 'King', '1 cama King-size. Mayor tamaño y confort.'),
(8, 'STU', 'Studio', 'Habitación con área de cocina integrada. Tipo estudio.'),
(9, 'STE', 'Suite', 'Habitación de lujo con sala de estar y espacio adicional.'),
(10, 'FAM', 'Familiar', 'Diseñada para familias. Espacio y capacidad para adultos y niños.'),
(11, 'APT', 'Apartamento', 'Unidad independiente tipo apartamento. Cocina y sala.'),
(12, 'PET', 'Pet-Friendly', 'Apta para huéspedes que viajan con mascotas.'),
(13, 'ACC', 'Accesible', 'Adaptada para personas con movilidad reducida.');

```
