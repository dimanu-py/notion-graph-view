from behave import given, when, then
from fastapi.testclient import TestClient

from src.delivery.api.main import app

client = TestClient(app)


@given("I have a valid database ID")
def step_given_valid_database_id(context: dict) -> None:
    context.database_id = "2963e08dfb7219e68a785fc9d2dgd756"


@when("I make a GET request to /graph/<database_id>")
def step_when_make_get_request(context: dict) -> None:
    response = client.get(f"/graph/{context.database_id}")
    context.response = response


@then("I should get a 200 OK response")
def step_then_get_200_response(context: dict) -> None:
    assert context.response.status_code == 200


@then("the response JSON should be not empty")
def step_then_response_json_not_empty(context: dict) -> None:
    assert context.response.json() is not None
