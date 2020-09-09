DROP TABLE IF EXISTS data_history;
DROP TABLE IF EXISTS relevant_data;
DROP TABLE IF EXISTS parameter;

CREATE TABLE parameter (
    param_id UUID PRIMARY KEY,
    param_created_at TIMESTAMP NOT NULL,
    param_modified_at TIMESTAMP NULL,
    param_name TEXT NOT NULL,
    param_code TEXT NOT NULL UNIQUE,
    param_description TEXT NOT NULL,
    param_data_type VARCHAR(32) NOT NULL
);

CREATE TABLE relevant_data (
    data_id UUID PRIMARY KEY,
    data_record_date TIMESTAMP NOT NULL,
    data_created_at TIMESTAMP NOT NULL,
    data_modified_at TIMESTAMP NOT NULL,
    data_param_val JSONB NOT NULL,
    data_param_id UUID NOT NULL UNIQUE,
    FOREIGN KEY (data_param_id) REFERENCES parameter(param_id)
);

CREATE TABLE data_history (
    data_history_id UUID PRIMARY KEY,
    data_history_record_date TIMESTAMP NOT NULL,
    data_history_created_at TIMESTAMP NOT NULL,
    data_history_modified_at TIMESTAMP NOT NULL,
    data_history_param_val JSONB NOT NULL,
    data_history_param_id UUID NOT NULL,
    data_history_relevant_data_id UUID NOT NULL,
    FOREIGN KEY (data_history_param_id) REFERENCES parameter(param_id),
    FOREIGN KEY (data_history_relevant_data_id) REFERENCES relevant_data(data_id)
)



