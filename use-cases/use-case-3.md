 # USE CASE 3: As a user I want to produce a population report of a country containing the following columns: Code, Name Continent, Region, Population, Capital
## CHARACTERISTIC INFORMATION

### Goal in Context
As a user I want to produce a population report of a country containing the following columns: Code, Name Continent, Region, Population, Capital

### Scope
Organisation

### Level
Primary

### Preconditions

We have access to the population information required & access to the names of capital cities(Regions, District, Code, Name, Continent, Region, Population and Capital).

### Success End Condition

User can access capital cities, regional or district population with columns Code, Name, Continent, Region, Population and Capital.

### Failed End Condition

User cannot access capital cities, regional or district population with columns Code, Name, Continent, Region, Population and Capital.


### Primary Actor

User

### Trigger

A request for access to population per continent information is made.

## MAIN SUCCESS SCENARIO

1. User requests population information for given capital city, region or district.
2. Population(Capital city, region, district, code, name, continent) information is shown to user.

## EXTENSIONS


2. **Condition**: action of sub use case
1. Population information for required continent is unavailable.

## SUB-VARIATIONS

None

## SCHEDULE

Final release