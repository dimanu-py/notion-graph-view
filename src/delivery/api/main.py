from dotenv import load_dotenv
from fastapi import FastAPI

from src.delivery.api.notes import find_all_notes_route

app = FastAPI()

load_dotenv()

app.include_router(find_all_notes_route.router)
