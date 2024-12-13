from src.contexts.shared.domain.exceptions.required_value_error import (
    RequiredValueError,
)
from src.contexts.shared.domain.value_objects.value_object import ValueObject


class RelatedNotes(ValueObject[dict]):
    def _validate(self, value: dict) -> None:
        if value is None:
            raise RequiredValueError
