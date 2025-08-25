create schema bio4_1;

create table bio4_1.v_bioactivity_data
(
    m4id                bigint,
    aeid                bigint,
    spid                varchar(50),
    chid                integer,
    casn                varchar(45),
    chnm                varchar(255),
    dsstox_substance_id varchar(255),
    bmad                double precision,
    resp_max            double precision,
    resp_min            double precision,
    max_mean            double precision,
    max_mean_conc       double precision,
    max_med             double precision,
    max_med_conc        double precision,
    logc_max            double precision,
    logc_min            double precision,
    nconc               integer,
    npts                integer,
    nrep                double precision,
    nmed_gtbl           integer,
    tmpi                integer,
    m5id                bigint,
    modl                varchar(5),
    hitc                double precision,
    fitc                smallint,
    coff                double precision,
    actp                double precision,
    model_type          smallint,
    chid_rep            smallint,
    stkc                double precision,
    stkc_unit           varchar(50),
    tested_conc_unit    varchar(50),
    mc3_param           json,
    mc4_param           json,
    mc5_param           json,
    mc6_param           json
);

create table bio4_1.v_assay_annotation
(
    aeid                          integer,
    assay_component_endpoint_name varchar(255),
    export_ready                  integer,
    internal_ready                integer,
    assay_component_endpoint_desc text,
    assay_function_type           varchar(255),
    normalized_data_type          varchar(255),
    burst_assay                   integer,
    key_positive_control          varchar(255),
    signal_direction              varchar(255),
    intended_target_type          varchar(255),
    intended_target_type_sub      varchar(255),
    intended_target_family        varchar(255),
    intended_target_family_sub    varchar(255),
    cell_viability_assay          integer,
    data_usability                integer,
    acid                          integer,
    assay_component_name          varchar(255),
    assay_component_desc          text,
    assay_component_target_desc   text,
    parameter_readout_type        varchar(255),
    assay_design_type             varchar(255),
    assay_design_type_sub         varchar(255),
    biological_process_target     varchar(255),
    detection_technology_type     varchar(255),
    detection_technology_type_sub varchar(255),
    detection_technology          varchar(255),
    key_assay_reagent_type        varchar(255),
    key_assay_reagent             varchar(255),
    technological_target_type     varchar(255),
    technological_target_type_sub varchar(255),
    aid                           integer,
    assay_name                    varchar(255),
    assay_desc                    text,
    timepoint_hr                  double precision,
    organism_id                   integer,
    organism                      varchar(255),
    tissue                        varchar(255),
    cell_format                   varchar(255),
    cell_free_component_source    varchar(255),
    cell_short_name               varchar(255),
    cell_growth_mode              varchar(255),
    assay_footprint               varchar(255),
    assay_format_type             varchar(255),
    assay_format_type_sub         varchar(255),
    content_readout_type          varchar(255),
    dilution_solvent              varchar(255),
    dilution_solvent_percent_max  double precision,
    asid                          integer,
    assay_source_name             varchar(255),
    assay_source_long_name        varchar(255),
    assay_source_desc             varchar(255),
    gene                          json,
    assay_list                    json,
    citations                     json
);


create table bio4_1.v_assay_list_counts
(
    aeid      bigint,
    active_mc bigint,
    total_mc  bigint,
    active_sc bigint,
    total_sc  bigint
);

create schema app;

create table app.api_keys
(
    api_key    uuid not null
        constraint apl_keys_pk
            primary key,
    data_scope varchar(10) default 'PUBLIC'::character varying,
    created_on date        default now()
);

comment on table app.api_keys is 'table to store API keys';

comment on column app.api_keys.data_scope is 'if anyone connected this user to us.';

