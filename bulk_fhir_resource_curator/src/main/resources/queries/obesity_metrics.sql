SELECT gender,
       year(observation.issued) AS year_issued,
       CASE
           WHEN (year(observation.issued) - year(patient.birth_date)) < 15 THEN '14 or younger'
           WHEN (year(observation.issued) - year(patient.birth_date)) >= 15
                AND (year(observation.issued) - year(patient.birth_date)) <= 24 THEN '15-24'
           WHEN (year(observation.issued) - year(patient.birth_date)) >= 25
                AND year(observation.issued) - year(patient.birth_date) <= 34 THEN '25-34'
           WHEN year(observation.issued) - year(patient.birth_date) >= 35
                AND year(observation.issued) - year(patient.birth_date) <= 44 THEN '35-44'
           WHEN year(observation.issued) - year(patient.birth_date) >= 45
                AND year(observation.issued) - year(patient.birth_date) <= 54 THEN '45-54'
           WHEN year(observation.issued) - year(patient.birth_date) >= 55
                AND year(observation.issued) - year(patient.birth_date) <= 64 THEN '55-64'
           WHEN year(observation.issued) - year(patient.birth_date) >= 65
                AND year(observation.issued) - year(patient.birth_date) <= 74 THEN '65-74'
           WHEN year(observation.issued) - year(patient.birth_date) >= 75
                AND year(observation.issued) - year(patient.birth_date) <= 84 THEN '75-84'
           WHEN year(observation.issued) - year(patient.birth_date) >= 85 THEN '85-older'
       END AS age,
       COUNT(CASE
                 WHEN quantity_value < 18 THEN 1
             END) AS 'Underweight',
       COUNT(CASE
                 WHEN quantity_value >= 18
                      AND quantity_value < 24.9 THEN 1
             END) AS 'Healthy',
       COUNT(CASE
                 WHEN quantity_value >= 25
                      AND quantity_value < 29.9 THEN 1
             END) AS 'Overweight',
       COUNT(CASE
                 WHEN quantity_value >= 30 THEN 1
             END) AS 'Obese'
FROM observation,
     patient
WHERE observation.patient_id = patient.id
  AND observation.code_text = 'Body Mass Index'
  AND observation.issued =
    (SELECT issued
     FROM observation t2
     WHERE t2.patient_id = observation.patient_id
       AND year(observation.issued) = year(t2.issued)
     ORDER BY t2.issued DESC
     LIMIT 1)
GROUP BY gender,
         year_issued,
         age;