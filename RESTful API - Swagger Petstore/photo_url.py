class PhotoUrl:
    def __init__(self, photoUrl: str):
        self.photoUrl = photoUrl

    def __str__(self):
        return f'''"{self.photoUrl}"'''
