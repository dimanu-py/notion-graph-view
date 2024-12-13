from urllib.parse import urlparse

from src.contexts.shared.domain.value_objects.string_value_object import (
    StringValueObject,
)


class NoteUrl(StringValueObject):
    def __init__(self, value: str) -> None:
        super().__init__(value)

    def _validate(self, value: str) -> None:
        super()._validate(value)
        self._ensure_url_has_scheme_and_netloc(value)

    @staticmethod
    def _ensure_url_has_scheme_and_netloc(value: str) -> None:
        result = urlparse(value)
        all([result.scheme, result.netloc])
