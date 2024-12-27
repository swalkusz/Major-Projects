class Tag:
    def __init__(self, id: int, name: str):
        self.id = id
        self.name = name

    def __str__(self):
        return f'''{{"id": {self.id}, "name": "{self.name}"}}'''
