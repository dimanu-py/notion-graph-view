CREATE TABLE IF NOT EXISTS notes (
    id SERIAL PRIMARY KEY,
    notion_id VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL,
    related_notes JSONB NOT NULL DEFAULT '[]'
);