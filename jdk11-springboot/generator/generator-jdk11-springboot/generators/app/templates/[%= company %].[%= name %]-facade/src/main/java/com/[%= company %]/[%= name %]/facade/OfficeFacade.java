package com.[%= company %].[%= name %].facade;

import com.[%= company %].[%= name %].commons.dto.OfficeDto;
import com.[%= company %].[%= name %].commons.request.PaginatedRequestDto;
import com.[%= company %].[%= name %].commons.response.GenericResponseDto;
import com.[%= company %].[%= name %].commons.response.PaginatedResponseDto;

/**
 * @author guillermo.segura@axity.com
 */
public interface OfficeFacade
{
  /**
   * Método para buscar las oficinas
   * 
   * @param request
   * @return
   */
  PaginatedResponseDto<OfficeDto> findOffices( PaginatedRequestDto request );

  /**
   * Busca una oficina por código
   * 
   * @param officeCode
   * @return
   */
  GenericResponseDto<OfficeDto> find( String officeCode );

  /**
   * Crea una oficina
   * 
   * @param office
   * @return
   */
  GenericResponseDto<OfficeDto> create( OfficeDto office );

  /**
   * Actualiza una oficina
   * 
   * @param office
   * @return
   */
  GenericResponseDto<Boolean> update( OfficeDto office );

  /**
   * Elimina una oficina
   * 
   * @param officeCode
   * @return
   */
  GenericResponseDto<Boolean> delete( String officeCode );
}
