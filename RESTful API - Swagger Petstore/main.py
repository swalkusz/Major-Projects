import requests
from category import Category
from tag import Tag
from status import Status
from pet import Pet
from photo_url import PhotoUrl

url = 'https://petstore.swagger.io/v2/pet/'


def get(endpoint=""):
    response = requests.get(
        url + endpoint, headers={"accept": "application/json"})
    if response.status_code == 200:
        return response.json()
    else:
        print(f"Error GET: {response.status_code} - {response.text}")
        return None


def post(data=""):
    headers = {"accept": "application/json",
               "Content-Type": "application/json"}
    response = requests.post(url, headers=headers, data=data)
    if response.status_code in [200, 201]:
        print("Success POST!")
    else:
        print(f"Error POST: {response.status_code} - {response.text}")
    return response


def delete(pet_id=""):
    if pet_id != "":
        response = requests.delete(
            url + pet_id, headers={"accept": "application/json"})
        if response.status_code == 200:
            print(f"Success! Pet with id: {pet_id} has been deleted")
        else:
            print(f"Error DELETE: {response.status_code} - {response.text}")


def create_pet():
    category = Category(id=1, name="Dog")
    photoUrls = [PhotoUrl("url 1"), PhotoUrl("url 2")]
    tags = [Tag(id=1, name="tag 1"), Tag(id=2, name="tag 2")]
    status = Status.AVAILABLE
    return Pet(id=76, category=category, name="Snoop dog",
               photoUrls=photoUrls, tags=tags, status=status)


def main():
    pet = create_pet()
    post(data=str(pet))
    print(get(str(pet.id)))
    delete(pet_id="76")


if __name__ == "__main__":
    main()
