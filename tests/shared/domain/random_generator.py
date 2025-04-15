from faker import Faker


class RandomGenerator:
    faker = Faker()

    @classmethod
    def uuid(cls) -> str:
        return str(cls.faker.uuid4(cast_to=str))

    @classmethod
    def url(cls) -> str:
        return cls.faker.url()

    @classmethod
    def title(cls) -> str:
        return cls.faker.sentence()
