from notion_client import Client
from notion_client.api_endpoints import DatabasesEndpoint


class NotionClient:
    def __init__(self, auth_token: str) -> None:
        self._client = Client(auth=auth_token)

    @property
    def databases(self) -> DatabasesEndpoint:
        return self._client.databases
