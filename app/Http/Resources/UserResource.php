<?php

declare(strict_types=1);

namespace App\Http\Resources;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;
use OpenApi\Attributes as OA;

/**
 * @mixin User
 */
#[OA\Schema(
    description: 'Информация о залогиненном пользователе',
    properties: [
        new OA\Property(
            property: 'uuid',
            description: 'UUID пользователя',
            type: 'string',
            format: 'uuid',
        ),
    ],
)]
class UserResource extends JsonResource
{
    /**
     * @return array<string, mixed>
     */
    public function toArray(Request $request): array
    {
        return [
            'uuid' => $this->uuid,
        ];
    }
}
