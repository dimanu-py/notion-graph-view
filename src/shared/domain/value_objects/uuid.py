from uuid import UUID

from src.shared.domain.exceptions.required_value_error import (
    RequiredValueError,
)
from src.shared.domain.value_objects.value_object import ValueObject


class Uuid(ValueObject[str]):
    def __init__(self, value: str) -> None:
        super().__init__(value)

    def _validate(self, value: str) -> None:
        if value is None:
            raise RequiredValueError
        UUID(value)
