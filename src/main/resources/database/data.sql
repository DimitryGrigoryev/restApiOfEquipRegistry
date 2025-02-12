-- insert into equip_type(code, name)
-- VALUES ('Televisions', 'Телевизоры'),
--        ('VacuumCleaners', 'Пылесосы'),
--        ('Refrigerators', 'Холодильники'),
--        ('SmartPhones', 'Смартфоны'),
--        ('Desktops', 'Компьютеры');

insert into equip_type(name)
VALUES ('Телевизоры'),
       ('Пылесосы'),
       ('Холодильники'),
       ('Смартфоны'),
       ('Компьютеры');

insert into equipment (name, equip_type_id, country, company, order_online, in_credit)
VALUES ('Телевизор Samsung', '1', 'CHINA', 'Samsung', true, true),
       ('Телевизор Panasonic', '1', 'CHINA', 'Panasonic', true, true),
       ('Пылесос Samsung', '2', 'CHINA', 'Samsung', true, true),
       ('Пылесос Panasonic', '2', 'CHINA', 'Panasonic', true, true),
       ('Холодильник Samsung', '3', 'CHINA', 'Samsung', true, true),
       ('Холодильник Panasonic', '3', 'CHINA', 'Panasonic', true, true),
       ('Смартфон Samsung', '4', 'CHINA', 'Samsung', true, true),
       ('Смартфон Panasonic', '4', 'CHINA', 'Panasonic', true, true),
       ('Компьютер Samsung', '5', 'CHINA', 'Samsung', true, true),
       ('Компьютер Panasonic', '5', 'CHINA', 'Panasonic', true, true);


insert into model (name, equipment_id, serial_num, color, size, price, available)
VALUES ('SamSL-3',     '1',  'L2323232', 'grey', 59, 5.00, true),
       ('SamSL-16',     '1',  'L23213232', 'blue',  59, 1001.00, false),
       ('SamSL-10',     '2',  'L23213232', 'white',  59, 1001.00, true),
       ('SamSL-410',    '2',  'L232213232', 'white',  59, 100.00, true),
       ('SamSL-311',    '3',  'L232132132', 'grey',  59, 100.00, true),
       ('SamSL-310',    '3',  'L2321322132', 'grey',  59, 100.00, true),
       ('SamSL-403',    '4',  'L2321321321', 'white',  59, 99.00, false),
       ('SamSL-402',    '4',  'L23213221321', 'white',  59, 100.00, true),
       ('SSL-40313',    '5',  'L23211321321', 'grey',  59, 100.00, true),
       ('SSL-40314',    '5',  'L232112321321', 'grey',  59, 399.00, true),
       ('SampL-41310', '6', 'L232113213121', 'white',  59, 100.00, true),
       ('SampL-42310', '6', 'L2321213213121', 'white',  59, 100.00, false),
       ('SamhL-40320', '7', 'L23211323121', 'grey',  59, 100.00, true),
       ('SamhL-40350', '7', 'L232113223121', 'grey',  59, 100.00, true),
       ('SamSL-50311', '8',  'L2321323121', 'white',  59, 100.00, false),
       ('SamSL-50310', '8',  'L23212323121', 'white',  59, 4566.00, true),
       ('SamSt-40110', '9',  'L232133121', 'grey',  59, 100.00, true),
       ('SamSt-40010', '9',  'L2321233121', 'grey',  59, 100.00, false),
       ('SL-40310',    '10',  'L23213121', 'white',  59, 33.22, true),
       ('SL-40410',     '10',  'L232123121', 'white',  59, 100.01, true);



insert into options (name, description, model_id)
VALUES ('Category'  ,        'Big',  '1'               ),
       ('Tehnology' ,       'OLED',  '1'               ),
       ('Category'  ,        'Big',  '2'               ),
       ('Tehnology' ,       'OLED',  '2'               ),
       ('Category'  ,        'Big',  '3'              ),
       ('Tehnology' ,       'OLED',  '3'              ),
       ('Category'  ,        'Big', '4'              ),
       ('Tehnology' ,       'OLED', '4'              ),
       ('Value'     ,      '55',    '5'              ),
       ('CountSwhitch',    '3' ,    '5'              ),
       ('Value'     ,     '55',     '6'              ),
       ('CountSwhitch',   '3' ,     '6'              ),
       ('Value'     ,     '55',     '7'              ),
       ('CountSwhitch',   '3' ,     '7'              ),
       ('Value'     ,     '55',     '8'              ),
       ('CountSwhitch',   '3' ,     '8'              ),
       ('CountDoors',        '2',      '9'           ),
       ('TypeCompressor',   'freeon',   '9'           ),
       ('CountDoors',        '2',      '10'            ),
       ('TypeCompressor',   'freeon',   '10'           ),
       ('CountDoors',         '2',     '11'        ),
       ('TypeCompressor',    'freeon',  '11'         ),
       ('CountDoors',         '2',     '12'          ),
       ('TypeCompressor',    'freeon',  '12'         ),
       ('Memory',       '512',       '13'             ),
       ('CountCamers',  '3',         '13'             ),
       ('Memory',       '512',        '14'            ),
       ('CountCamers',  '3',          '14'            ),
       ('Memory',        '512',       '15'            ),
       ('CountCamers',   '3',         '15'            ),
       ('Memory',       '512',        '16'            ),
       ('CountCamers',  '3',          '16'            ),
       ('Category',           'Home', '17'            ),
       ('TypeProcessor',      'Dual', '17'            ),
       ('Category',           'Home', '18'            ),
       ('TypeProcessor',      'Dual', '18'            ),
       ('Category',           'Home', '19'                ),
       ('TypeProcessor',      'quatro', '19'                ),
       ('Category',           'Home', '20'                ),
       ('TypeProcessor',      'Dual', '20'                );
