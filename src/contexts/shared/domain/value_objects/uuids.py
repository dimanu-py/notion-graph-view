from uuid import UUID

from src.contexts.shared.domain.exceptions.required_value_error import (
    RequiredValueError,
)
from src.contexts.shared.domain.value_objects.value_object import ValueObject


class Uuids(ValueObject[list[str]]):
    def _validate(self, value: list[str]) -> None:
        if value is None:
            raise RequiredValueError
        for v in value:
            UUID(v)
