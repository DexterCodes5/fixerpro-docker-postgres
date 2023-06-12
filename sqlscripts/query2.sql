USE fixerpro;

SELECT * FROM engine_oil WHERE (title LIKE 'CASTROL%' OR title LIKE 'MOTUL%') AND (liters = 1);