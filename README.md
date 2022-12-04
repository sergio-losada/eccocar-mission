# mission
[https://mission-sergio-losada.cloud.okteto.net/swagger-ui.html]
## REST API for managing space missions:
### Fields:
 - id: mission identifier
 - name: mission name
 - missionStartDate: mission start date
 - starship: starship to be used in the mission
 - captains: people who are going to the mission
 - crew: number of additional crew members for the mission
 - planets: planets that have to be traveled for the mission

### Endpoints
 - POST /mission: insert space mission
 - GET /mission: retrieve existing missions
 - GET /mission?captain=1,2...: retrieve existing missions where the given captains are present 
 - GET /mission/recommend?criteria=X: mission recommender regarding X (reward or ratio reward/hours)