SELECT cis_grp_bdpm.id_cis, cis_cip_bdpm.libele, cis_grp_bdpm.id_cis_grp, cis_grp_bdpm.voie_administration, 
		cis_grp_bdpm,libele_grp_gen
FROM cis_cip_bdpm INNER JOIN cis_grp_bdpm
ON cis_cip_bdpm.id = cis_grp_bdpm.id_cis
ORDER BY cis_cip_bdpm.libele ASC 
