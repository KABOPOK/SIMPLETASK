CREATE TABLE sos_forms (
  id UUID PRIMARY KEY,
  user_id UUID NOT NULL,
  telegram_contact VARCHAR(64) NOT NULL,
  deactivation_code TEXT NOT NULL,
  address TEXT NOT NULL,
  date_time TIMESTAMP NOT NULL,
  comment TEXT,
  is_ready BOOLEAN DEFAULT FALSE,
  last_activation TIMESTAMP
);