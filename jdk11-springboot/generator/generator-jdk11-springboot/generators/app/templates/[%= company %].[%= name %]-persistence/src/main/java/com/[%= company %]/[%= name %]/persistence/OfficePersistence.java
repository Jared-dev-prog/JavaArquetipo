package com.[%= company %].[%= name %].persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.[%= company %].[%= name %].model.OfficeDO;

/**
 * Interface de persistencia de {@link com.[%= company %].[%= name %].model.OfficeDO}
 * @author guillermo.segura@axity.com
 */
@Repository
public interface OfficePersistence extends JpaRepository<OfficeDO, String>
{
  
  /**
   * Busca las oficinas por ciudad
   * 
   * @param city
   * @return
   */
  List<OfficeDO> findByCity( String city );

  /**
   * Busca las oficinas por territorio
   * 
   * @param territory
   * @return
   */
  List<OfficeDO> findByTerritory( String territory );

  /**
   * Busca las oficinas por país
   * 
   * @param country
   * @return
   */
  List<OfficeDO> findByCountry( String country );
}
