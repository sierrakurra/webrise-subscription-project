INSERT INTO platforms (id, name) VALUES
('f12003b6-8a93-4e17-a0d0-d4ba7f0f0856', 'Youtube Premium'),
('fa733bb2-6d1f-4013-8a80-442f7476d44a', 'VK Музыка'),
('881bceb7-b13b-46d2-aeb3-2d3a2fca1f97', 'Яндекс.Плюс'),
('dc49701f-8da9-4aa3-b1c4-ca7ca8438563', 'Netflix')
ON CONFLICT (id) DO UPDATE
    SET name = EXCLUDED.name;