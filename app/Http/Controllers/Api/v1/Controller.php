<?php

namespace App\Http\Controllers\Api\v1;

use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use Illuminate\Foundation\Validation\ValidatesRequests;
use Illuminate\Routing\Controller as BaseController;
use OpenApi\Attributes as OA;

#[OA\Tag(
    name: 'login',
    description: 'Аутентификация',
)]
#[OA\Tag(
    name: 'v1',
    description: 'Версия API v1',
)]
#[OA\Info(
    version: '0.1',
    title: 'A-Money PHP Admin Service',
)]
#[OA\Schema(
    schema: 'ValidationErrors',
    properties: [
        new OA\Property(
            property: 'errors',
            type: 'object',
            example: [
                'Название поля' => ['Сообщения об ошибках'],
            ],
            additionalProperties: new OA\AdditionalProperties(
                type: 'array',
                items: new OA\Items(type: 'string'),
            ),
        ),
    ],
    type: 'object',
)]
class Controller extends BaseController
{
    use AuthorizesRequests, ValidatesRequests;
}
