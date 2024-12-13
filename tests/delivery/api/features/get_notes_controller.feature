Feature: Notes endpoint returns all notes with its related notes
	Scenario: Valid request to /notes/{database_id}
		Given I have a valid database ID
		When I make a GET request to /notes/<database_id>
		Then I should get a 200 OK response
		And the response JSON should be not empty