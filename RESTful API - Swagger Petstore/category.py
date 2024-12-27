class Category:
    def __init__(self, id: int, name: str):
        self.id = id
        self.name = name

    def __str__(self):
        str = (f'''{{"id": {self.id}, "name": "{self.name}"}}''')
        return str
