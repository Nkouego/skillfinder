-- ============================================
-- SkillFinder Database Schema
-- PostgreSQL
-- ============================================

-- ==========================
-- ENUMS
-- ==========================

CREATE TYPE user_role AS ENUM (
    'RECRUTEUR',
    'ADMIN_RH'
);

CREATE TYPE contract_type AS ENUM (
    'CDD',
    'CDI',
    'FREELANCE',
    'STAGE'
);

CREATE TYPE job_offer_status AS ENUM (
    'OUVERT',
    'FERME'
);

CREATE TYPE gender AS ENUM (
    'MASCULIN',
    'FEMININ'
);

CREATE TYPE application_status AS ENUM (
    'RECUE',
    'EN_ANALYSE',
    'ENTRETIEN',
    'ACCEPTEE',
    'REFUSEE'
);

-- ==========================
-- USERS
-- ==========================

CREATE TABLE users (

    id UUID PRIMARY KEY,

    full_name VARCHAR(150) NOT NULL,

    email VARCHAR(150) UNIQUE NOT NULL,

    password VARCHAR(255) NOT NULL,

    role user_role NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP

);

-- ==========================
-- JOB OFFERS
-- ==========================

CREATE TABLE job_offer (

    id UUID PRIMARY KEY,

    recruiter_id UUID NOT NULL,

    title VARCHAR(150) NOT NULL,

    description TEXT NOT NULL,

    contract_type contract_type NOT NULL,

    location VARCHAR(120),

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    closing_date TIMESTAMP,

    status job_offer_status NOT NULL,

    CONSTRAINT fk_offer_recruiter
        FOREIGN KEY(recruiter_id)
        REFERENCES users(id)

);

-- ==========================
-- RESUME
-- ==========================

CREATE TABLE resume (

    id UUID PRIMARY KEY,

    file_name VARCHAR(255) NOT NULL,

    file_path VARCHAR(500) NOT NULL,

    file_size BIGINT NOT NULL,

    file_type VARCHAR(50),

    uploaded_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP

);

-- ==========================
-- SKILLS
-- ==========================

CREATE TABLE skill (

    id UUID PRIMARY KEY,

    name VARCHAR(100) UNIQUE NOT NULL

);

-- ==========================
-- RESUME_SKILL
-- ==========================

CREATE TABLE resume_skill (

    resume_id UUID,

    skill_id UUID,

    PRIMARY KEY(resume_id, skill_id),

    CONSTRAINT fk_resume_skill_resume
        FOREIGN KEY(resume_id)
        REFERENCES resume(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_resume_skill_skill
        FOREIGN KEY(skill_id)
        REFERENCES skill(id)
        ON DELETE CASCADE

);

-- ==========================
-- LANGUAGES
-- ==========================

CREATE TABLE language (

    id UUID PRIMARY KEY,

    name VARCHAR(100) UNIQUE NOT NULL

);

-- ==========================
-- RESUME_LANGUAGE
-- ==========================

CREATE TABLE resume_language (

    resume_id UUID,

    language_id UUID,

    PRIMARY KEY(resume_id, language_id),

    CONSTRAINT fk_resume_language_resume
        FOREIGN KEY(resume_id)
        REFERENCES resume(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_resume_language_language
        FOREIGN KEY(language_id)
        REFERENCES language(id)
        ON DELETE CASCADE

);

-- ==========================
-- EXPERIENCE
-- ==========================

CREATE TABLE experience (

    id UUID PRIMARY KEY,

    resume_id UUID NOT NULL,

    job_title VARCHAR(150) NOT NULL,

    company VARCHAR(150) NOT NULL,

    description TEXT,

    start_date DATE NOT NULL,

    end_date DATE,

    CONSTRAINT fk_experience_resume
        FOREIGN KEY(resume_id)
        REFERENCES resume(id)
        ON DELETE CASCADE

);

-- ==========================
-- EDUCATION
-- ==========================

CREATE TABLE education (

    id UUID PRIMARY KEY,

    resume_id UUID NOT NULL,

    degree VARCHAR(150) NOT NULL,

    institution VARCHAR(150) NOT NULL,

    field_of_study VARCHAR(150),

    start_date DATE NOT NULL,

    end_date DATE,

    CONSTRAINT fk_education_resume
        FOREIGN KEY(resume_id)
        REFERENCES resume(id)
        ON DELETE CASCADE

);

-- ==========================
-- JOB APPLICATION
-- ==========================

CREATE TABLE job_application (

    id UUID PRIMARY KEY,

    job_offer_id UUID NOT NULL,

    resume_id UUID NOT NULL UNIQUE,

    first_name VARCHAR(100) NOT NULL,

    last_name VARCHAR(100) NOT NULL,

    email VARCHAR(150) NOT NULL,

    phone_number VARCHAR(30),

    birth_date DATE,

    gender gender,

    status application_status NOT NULL,

    applied_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_application_offer
        FOREIGN KEY(job_offer_id)
        REFERENCES job_offer(id),

    CONSTRAINT fk_application_resume
        FOREIGN KEY(resume_id)
        REFERENCES resume(id)

);