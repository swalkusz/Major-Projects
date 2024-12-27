from category import Category
from tag import Tag
from status import Status
from photo_url import PhotoUrl


class Pet:

    def __init__(self, id: int, category: Category, name: str, photoUrls: list[PhotoUrl], tags: list[Tag], status: Status):
        self.id = id
        self.category = category
        self.name = name
        self.photoUrls = photoUrls
        self.tags = tags
        self.status = status

    def __str__(self):
        tags_str = ", ".join(str(tag) for tag in self.tags)
        photos_str = ", ".join(str(photoUrl) for photoUrl in self.photoUrls)
        return (
            f'''
{{
    "id": {self.id},
    "category": {self.category},
    "name": "{self.name}",
    "photoUrls": [{photos_str}],
    "tags": [{tags_str}],
    "status": "{self.status.value}"
}}
'''
        )
