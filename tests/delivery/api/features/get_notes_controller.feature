Feature: Graph endpoint returns all notes with its related notes
	Scenario: Valid request to /graph/{database_id}
		Given I have a valid database ID
		When I make a GET request to /graph/<database_id>
		Then I should get a 200 OK response
		And the response JSON should be not empty